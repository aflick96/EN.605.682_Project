package edu.fin.models.investment;

import java.time.LocalDate;

public class WhatIfScenarioRow {
    private LocalDate weekStart;
    private double realContribution;
    private double scenarioContribution;
    private double totalContributions;
    private double growthThisWeek;
    private double totalGrowth;
    private double endBalance;

    public WhatIfScenarioRow() {}

    public LocalDate getWeekStart() { return weekStart; }
    public void setWeekStart(LocalDate weekStart) { this.weekStart = weekStart; }

    public double getRealContribution() { return realContribution; }
    public void setRealContribution(double realContribution) { this.realContribution = realContribution; }

    public double getScenarioContribution() { return scenarioContribution; }
    public void setScenarioContribution(double scenarioContribution) { this.scenarioContribution = scenarioContribution; }

    public double getTotalContributions() { return totalContributions; }
    public void setTotalContributions(double totalContributions) { this.totalContributions = totalContributions; }

    public double getGrowthThisWeek() { return growthThisWeek; }
    public void setGrowthThisWeek(double growthThisWeek) { this.growthThisWeek = growthThisWeek; }

    public double getTotalGrowth() { return totalGrowth; }
    public void setTotalGrowth(double totalGrowth) { this.totalGrowth = totalGrowth; }

    public double getEndBalance() { return endBalance; }
    public void setEndBalance(double endBalance) { this.endBalance = endBalance; }
}
