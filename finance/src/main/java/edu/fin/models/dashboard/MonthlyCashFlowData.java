/*
 * MonthlyCashFlowData.java
 * 
 * This class represents the monthly cash flow data for a user. It contains the month, income, and expenses for that month.
 */
package edu.fin.models.dashboard;

public class MonthlyCashFlowData {
    private String month;
    private double income;
    private double expenses;
    private double netCashFlow;

    // constructors
    public MonthlyCashFlowData() {}
    public MonthlyCashFlowData(String month, double income, double expenses, double netCashFlow) {
        this.month = month;
        this.income = income;
        this.expenses = expenses;
        this.netCashFlow = netCashFlow;
    }

    // getters and setters
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public double getIncome() { return income; }
    public void setIncome(double income) { this.income = income; }

    public double getExpenses() { return expenses; }
    public void setExpenses(double expenses) { this.expenses = expenses;}

    public double getNetCashFlow() { return netCashFlow; }
    public void setNetCashFlow(double netCashFlow) { this.netCashFlow = netCashFlow; }

    @Override
    public String toString() {
        return "MonthlyCashFlowData{" +
                "month='" + month + '\'' +
                ", income=" + income +
                ", expenses=" + expenses +
                ", netCashFlow=" + netCashFlow +
                '}';
    }
}

