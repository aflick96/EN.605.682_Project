package edu.fin.services;

import edu.fin.dtos.dashboard.*;
import edu.fin.dtos.income.*;
import edu.fin.repositories.income.*;
import edu.fin.repositories.user.*;
import edu.fin.entities.income.*;
import edu.fin.entities.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Service
public class DashboardService {
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

    public ExpenseByCategory getExpensesByCategory(Long userId) {
        // Check if the user exists
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;

        return expenseService.getExpenseCategoryTotals(userId);
    }
        

    private LocalDate getFirstOfMonth(LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth(), 1);
    }
}