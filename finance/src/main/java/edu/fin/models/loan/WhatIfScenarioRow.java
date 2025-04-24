package edu.fin.models.loan;

import java.time.LocalDate;

public class WhatIfScenarioRow {
    private LocalDate monthStartDate;
    // payments
    private double scenarioPayment;
    private double paymentThisMonth;
    private double principalThisMonth;
    private double interestThisMonth;
    // totals
    private double totalPrincipal;
    private double totalInterest;
    private double totalPaid;
    //balances
    private double principalRemaining;
    private double endBalance;
    // actual payments
    private double realPayment;

    public WhatIfScenarioRow() {}
    public WhatIfScenarioRow(LocalDate monthStartDate, double scenarioPayment, double paymentThisMonth, double principalThisMonth, double interestThisMonth,
                          double totalPrincipal, double totalInterest, double totalPaid, double principalRemaining,
                          double endBalance, double realPayment) {
        this.monthStartDate = monthStartDate;
        this.scenarioPayment = scenarioPayment;
        this.paymentThisMonth = paymentThisMonth;
        this.principalThisMonth = principalThisMonth;
        this.interestThisMonth = interestThisMonth;
        this.totalPrincipal = totalPrincipal;
        this.totalInterest = totalInterest;
        this.totalPaid = totalPaid;
        this.principalRemaining = principalRemaining;
        this.endBalance = endBalance;
        this.realPayment = realPayment;
    }

    public LocalDate getMonthStartDate() { return monthStartDate; }
    public void setMonthStartDate(LocalDate monthStartDate) { this.monthStartDate = monthStartDate; }

    public double getScenarioPayment() { return scenarioPayment; }
    public void setScenarioPayment(double scenarioPayment) { this.scenarioPayment = scenarioPayment; }

    public double getPaymentThisMonth() { return paymentThisMonth; }
    public void setPaymentThisMonth(double paymentThisMonth) { this.paymentThisMonth = paymentThisMonth; }

    public double getPrincipalThisMonth() { return principalThisMonth; }
    public void setPrincipalThisMonth(double principalThisMonth) { this.principalThisMonth = principalThisMonth; }

    public double getInterestThisMonth() { return interestThisMonth; }
    public void setInterestThisMonth(double interestThisMonth) { this.interestThisMonth = interestThisMonth; }

    public double getTotalPrincipal() { return totalPrincipal; }
    public void setTotalPrincipal(double totalPrincipal) { this.totalPrincipal = totalPrincipal; }

    public double getTotalInterest() { return totalInterest; }
    public void setTotalInterest(double totalInterest) { this.totalInterest = totalInterest; }

    public double getTotalPaid() { return totalPaid; }
    public void setTotalPaid(double totalPaid) { this.totalPaid = totalPaid; }

    public double getPrincipalRemaining() { return principalRemaining; }
    public void setPrincipalRemaining(double principalRemaining) { this.principalRemaining = principalRemaining; }

    public double getEndBalance() { return endBalance; }
    public void setEndBalance(double endBalance) { this.endBalance = endBalance; }

    public double getRealPayment() { return realPayment; }
    public void setRealPayment(double realPayment) { this.realPayment = realPayment; }

    @Override
    public String toString() {
        return "WhatIfScenarioRow{" +
                "monthStartDate=" + monthStartDate +
                ", scenarioPayment=" + scenarioPayment +
                ", principalThisMonth=" + principalThisMonth +
                ", interestThisMonth=" + interestThisMonth +
                ", totalPrincipal=" + totalPrincipal +
                ", totalInterest=" + totalInterest +
                ", totalPaid=" + totalPaid +
                ", principalRemaining=" + principalRemaining +
                ", endBalance=" + endBalance +
                ", realPayment=" + realPayment +
                '}';
    }
}
