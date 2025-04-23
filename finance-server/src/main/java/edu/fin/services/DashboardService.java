package edu.fin.services;

import edu.fin.dtos.dashboard.*;
import edu.fin.dtos.expense.*;
import edu.fin.dtos.income.*;
import edu.fin.dtos.investment.*;
import edu.fin.dtos.loan.*;

import edu.fin.repositories.expense.*;
import edu.fin.repositories.income.*;
import edu.fin.repositories.investment.*;
import edu.fin.repositories.loan.*;
import edu.fin.repositories.user.*;

import edu.fin.entities.expense.*;
import edu.fin.entities.income.*;
import edu.fin.entities.investment.*;
import edu.fin.entities.loan.*;
import edu.fin.entities.user.*;

import edu.fin.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    @Autowired
    private UserService userService;

    @Autowired
    private IncomeByPayFrequencyService incomeByPayFrequencyService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private InvestmentService investmentService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncomeLogRepository incomeLogRepository;

    @Autowired
    private AdditionalIncomeRepository additionalIncomeRepository;

    @Autowired
    private PosttaxDeductionRepository posttaxDeductionRepository;

    @Autowired
    private PretaxDeductionRepository pretaxDeductionRepository;

    @Autowired
    private ExpenseLogRepository expenseLogRepository;

    @Autowired
    private ExpenseItemRepository expenseItemRepository;

    @Autowired
    private InvestmentLogRepository investmentLogRepository;

    @Autowired
    private InvestmentContributionRepository investmentContributionRepository;

    @Autowired
    private LoanItemRepository loanItemRepository;

    @Autowired
    private LoanPaymentRepository loanPaymentRepository;

    
    public List<NetWorth> getNetWorthOverTime(Long userId) {
        List<NetWorth> netWorthList = null;
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;

        Map<LocalDate, Double> netWorthMap = new HashMap<>();
        List<IncomeLog> income_logs = incomeLogRepository.findByUserId(userId);

        income_logs.forEach(log -> {
            List<IncomeByPayFrequencyDetail> details = incomeByPayFrequencyService.calculatePayDetail(log);
            details.forEach(detail -> {
                LocalDate date = getFirstOfMonth(detail.getPayDate());
                netWorthMap.put(date, netWorthMap.getOrDefault(date, 0.0) + detail.getNetIncome());
            });
        });

        Map<LocalDate, Double> expenseMap = expenseService.getExpensesByDate(userId);
        expenseMap.forEach((date, amount) -> {
            netWorthMap.put(date, netWorthMap.getOrDefault(date, 0.0) - amount);
        });

        Map<LocalDate, Double> investmentMap = investmentService.getInvestmentValueByDate(userId);
        investmentMap.forEach((date, amount) -> {
            netWorthMap.put(date, netWorthMap.getOrDefault(date, 0.0) + amount);
        });

        Map<LocalDate, Double> loanMap = loanService.getLoanItemValueByDate(userId);
        loanMap.forEach((date, amount) -> {
            netWorthMap.put(date, netWorthMap.getOrDefault(date, 0.0) + amount);
        });

        // create list of net worth objects that is sorted by date
        netWorthList = netWorthMap.entrySet().stream()
            .map(entry -> new NetWorth(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
        netWorthList.sort((n1, n2) -> n1.getDate().compareTo(n2.getDate()));
        
        return netWorthList;
    }

    public NetWorthBreakdown getNetWorthBreakdown(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;

        Map<LocalDate, Double> incomeMap = new HashMap<>();
        Map<LocalDate, Double> expenseMap = expenseService.getExpensesByDate(userId);
        Map<LocalDate, Double> investmentMap = investmentService.getInvestmentValueByDate(userId);
        Map<LocalDate, Double> loanMap = loanService.getLoanItemValueByDate(userId);

        // 1. Income
        List<IncomeLog> income_logs = incomeLogRepository.findByUserId(userId);
        income_logs.forEach(log -> {
            List<IncomeByPayFrequencyDetail> details = incomeByPayFrequencyService.calculatePayDetail(log);
            details.forEach(detail -> {
                LocalDate date = getFirstOfMonth(detail.getPayDate());
                incomeMap.put(date, incomeMap.getOrDefault(date, 0.0) + detail.getNetIncome());
            });
        });

        // 2. Net worth = income - expenses + investments + loan net value
        Map<LocalDate, Double> netWorthMap = new HashMap<>();
        Set<LocalDate> allDates = new HashSet<>();
        allDates.addAll(incomeMap.keySet());
        allDates.addAll(expenseMap.keySet());
        allDates.addAll(investmentMap.keySet());
        allDates.addAll(loanMap.keySet());

        for (LocalDate date : allDates) {
            double income = incomeMap.getOrDefault(date, 0.0);
            double expense = expenseMap.getOrDefault(date, 0.0);
            double investment = investmentMap.getOrDefault(date, 0.0);
            double loan = loanMap.getOrDefault(date, 0.0);

            double netWorth = income - expense + investment + loan;
            netWorthMap.put(date, netWorth);
        }

        NetWorthBreakdown result = new NetWorthBreakdown();
        result.setNetWorth(new TreeMap<>(netWorthMap));
        result.setIncome(new TreeMap<>(incomeMap));
        result.setExpenses(new TreeMap<>(expenseMap));
        result.setInvestments(new TreeMap<>(investmentMap));
        result.setLoans(new TreeMap<>(loanMap));

        return result;
    }

    private LocalDate getFirstOfMonth(LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth(), 1);
    }
}