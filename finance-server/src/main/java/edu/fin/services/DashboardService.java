package edu.fin.services;

import edu.fin.dtos.dashboard.*;
import edu.fin.repositories.user.*;
import edu.fin.entities.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    // Constructor
    public DashboardService() {}

    // Get the net worth and values for income, expenses, investments, and loans that make up the net worth line
    public NetWorthBreakdown getNetWorthBreakdown(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;

        Map<LocalDate, Double> incomeFlow = incomeByPayFrequencyService.getIncomeByDate(userId);
        Map<LocalDate, Double> expenseFlow = expenseService.getExpensesByDate(userId);
        Map<LocalDate, Double> investmentBal = investmentService.getInvestmentValueByDate(userId);
        Map<LocalDate, Double> loanBal = loanService.getLoanItemValueByDate(userId);

        Set<LocalDate> allDates = new TreeSet<>();
        allDates.addAll(incomeFlow.keySet());
        allDates.addAll(expenseFlow.keySet());
        allDates.addAll(investmentBal.keySet());
        allDates.addAll(loanBal.keySet());

        double cumulativeIncome = 0.0;
        double cumulativeExpense = 0.0;

        Map<LocalDate, Double> netWorthMap = new TreeMap<>();
        Map<LocalDate, Double> cumulativeIncomeMap = new TreeMap<>();
        Map<LocalDate, Double> cumulativeExpenseMap = new TreeMap<>();
        Map<LocalDate, Double> investmentBalanceMap = new TreeMap<>();
        Map<LocalDate, Double> loanBalanceMap = new TreeMap<>();

        for (LocalDate date : allDates) {
            cumulativeIncome += incomeFlow.getOrDefault(date, 0.0);
            cumulativeExpense += expenseFlow.getOrDefault(date, 0.0);
            double investmentBalance = investmentBal.getOrDefault(date, 0.0);
            double loanBalance = loanBal.getOrDefault(date, 0.0);
            double networth = cumulativeIncome - cumulativeExpense + investmentBalance - loanBalance;
            
            netWorthMap.put(date, networth);
            cumulativeIncomeMap.put(date, cumulativeIncome);
            cumulativeExpenseMap.put(date, cumulativeExpense);
            investmentBalanceMap.put(date, investmentBalance);
            loanBalanceMap.put(date, loanBalance);
        }

        NetWorthBreakdown result = new NetWorthBreakdown();
        result.setNetWorth(netWorthMap);
        result.setIncome(cumulativeIncomeMap);
        result.setExpenses(cumulativeExpenseMap);
        result.setInvestments(investmentBalanceMap);
        result.setLoans(loanBalanceMap);

        return result;
    }

    // Get the total expenses by category
    public ExpenseByCategory getExpensesByCategory(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;

        return expenseService.getExpenseCategoryTotals(userId);
    }

    // Get the total income and expense by month
    public MonthlyCashFlow getMonthlyCashFlow(Long uid) {
        User user = userRepository.findById(uid).orElse(null);
        if (user == null) return null;

        Map<LocalDate, Double> expenseByDate = expenseService.getExpensesByDate(uid);
        Map<LocalDate, Double> incomeByDate = incomeByPayFrequencyService.getIncomeByDate(uid);

        Map<YearMonth, Double> monthlyIncome = new HashMap<>();
        Map<YearMonth, Double> monthlyExpense = new HashMap<>();
        Set<YearMonth> allMonths = new HashSet<>();

        for (Map.Entry<LocalDate, Double> entry : incomeByDate.entrySet()) {
            YearMonth ym = YearMonth.from(entry.getKey());
            monthlyIncome.merge(ym, entry.getValue(), Double::sum);
            allMonths.add(ym);
        }

        for (Map.Entry<LocalDate, Double> entry : expenseByDate.entrySet()) {
            YearMonth ym = YearMonth.from(entry.getKey());
            monthlyExpense.merge(ym, entry.getValue(), Double::sum);
            allMonths.add(ym);
        }

        List<MonthlyCashFlowData> cashFlowData = allMonths.stream()
                .sorted()
                .map(month -> new MonthlyCashFlowData(
                    month.toString(),
                    monthlyIncome.getOrDefault(month, 0.0),
                    monthlyExpense.getOrDefault(month, 0.0),
                    monthlyIncome.getOrDefault(month, 0.0) - monthlyExpense.getOrDefault(month, 0.0)
                ))
                .collect(Collectors.toList());

        MonthlyCashFlow cashFlow = new MonthlyCashFlow();
        cashFlow.setCashFlowData(cashFlowData);
        return cashFlow;        
    }

    public LoanCompletion getLoanCompletions(Long uid) {
        User user = userRepository.findById(uid).orElse(null);
        if (user == null) return null;

        LoanCompletion loanCompletions = loanService.getLoanCompletions(uid);
        return loanCompletions;
    }
}