package edu.fin.models.dashboard;

import java.time.LocalDate;
import java.util.Map;

public class NetWorthBreakdown {
    private Map<LocalDate, Double> netWorth;
    private Map<LocalDate, Double> income;
    private Map<LocalDate, Double> expenses;
    private Map<LocalDate, Double> investments;
    private Map<LocalDate, Double> loans;

    public NetWorthBreakdown() {}

    public NetWorthBreakdown(Map<LocalDate, Double> netWorth, Map<LocalDate, Double> income, Map<LocalDate, Double> expenses, Map<LocalDate, Double> investments, Map<LocalDate, Double> loans) {
        this.netWorth = netWorth;
        this.income = income;
        this.expenses = expenses;
        this.investments = investments;
        this.loans = loans;
    }

    public Map<LocalDate, Double> getNetWorth() { return netWorth; }
    public void setNetWorth(Map<LocalDate, Double> netWorth) { this.netWorth = netWorth; }

    public Map<LocalDate, Double> getIncome() { return income; }
    public void setIncome(Map<LocalDate, Double> income) { this.income = income; }

    public Map<LocalDate, Double> getExpenses() { return expenses; }
    public void setExpenses(Map<LocalDate, Double> expenses) { this.expenses = expenses; }

    public Map<LocalDate, Double> getInvestments() { return investments; }
    public void setInvestments(Map<LocalDate, Double> investments) { this.investments = investments; }

    public Map<LocalDate, Double> getLoans() { return loans; }
    public void setLoans(Map<LocalDate, Double> loans) { this.loans = loans; }
    
    @Override
    public String toString() {
        return "NetWorthBreakdown{" +
                "netWorth=" + netWorth +
                ", income=" + income +
                ", expenses=" + expenses +
                ", investments=" + investments +
                ", loans=" + loans +
                '}';
    }
}
