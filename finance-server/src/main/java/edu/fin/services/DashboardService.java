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

        Map<LocalDate, Double> incomeMap = incomeByPayFrequencyService.getIncomeByDate(userId);
        Map<LocalDate, Double> expenseMap = expenseService.getExpensesByDate(userId);
        Map<LocalDate, Double> investmentMap = investmentService.getInvestmentValueByDate(userId);
        Map<LocalDate, Double> loanMap = loanService.getLoanItemValueByDate(userId);

        Set<LocalDate> allDates = new HashSet<>();
        allDates.addAll(incomeMap.keySet());
        allDates.addAll(expenseMap.keySet());
        allDates.addAll(investmentMap.keySet());
        allDates.addAll(loanMap.keySet());

        double cumulativeIncome = 0.0;
        double cumulativeExpense = 0.0;
        double cumulativeInvestment = 0.0;
        double cumulativeLoan = 0.0;

        Map<LocalDate, Double> netWorthMap = new TreeMap<>();
        Map<LocalDate, Double> cumulativeIncomeMap = new TreeMap<>();
        Map<LocalDate, Double> cumulativeExpenseMap = new TreeMap<>();
        Map<LocalDate, Double> cumulativeInvestmentMap = new TreeMap<>();
        Map<LocalDate, Double> cumulativeLoanMap = new TreeMap<>();

        for (LocalDate date : allDates) {
            cumulativeIncome += incomeMap.getOrDefault(date, 0.0);
            cumulativeExpense += expenseMap.getOrDefault(date, 0.0);
            cumulativeInvestment += investmentMap.getOrDefault(date, 0.0);
            cumulativeLoan += loanMap.getOrDefault(date, 0.0);

            double netWorth = cumulativeIncome - cumulativeExpense + cumulativeInvestment + cumulativeLoan;
            netWorthMap.put(date, netWorth);
            cumulativeIncomeMap.put(date, cumulativeIncome);
            cumulativeExpenseMap.put(date, cumulativeExpense);
            cumulativeInvestmentMap.put(date, cumulativeInvestment);
            cumulativeLoanMap.put(date, cumulativeLoan);
        }

        NetWorthBreakdown result = new NetWorthBreakdown();
        result.setNetWorth(new TreeMap<>(netWorthMap));
        result.setIncome(new TreeMap<>(cumulativeIncomeMap));
        result.setExpenses(new TreeMap<>(cumulativeExpenseMap));
        result.setInvestments(new TreeMap<>(cumulativeInvestmentMap));
        result.setLoans(new TreeMap<>(cumulativeLoanMap));

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