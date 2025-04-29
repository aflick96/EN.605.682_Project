/*
 * InvestmentScenarioResult.java
 * 
 * This class represents the result of an investment scenario calculation.
 */

package edu.fin.models.investment;

import java.util.List;

public class InvestmentScenarioResult {
    private double scenarioContribution;
    private double expectedReturn;
    private List<WhatIfScenarioRow> tableRows;

    // constructor
    public InvestmentScenarioResult(double scenarioContribution, double expectedReturn, List<WhatIfScenarioRow> tableRows) {
        this.scenarioContribution = scenarioContribution;
        this.expectedReturn = expectedReturn;
        this.tableRows = tableRows;
    }

    // getters and setters
    public double getScenarioContribution() { return scenarioContribution; }
    public void setScenarioContribution(double scenarioContribution) { this.scenarioContribution = scenarioContribution; }

    public double getExpectedReturn() { return expectedReturn; }
    public void setExpectedReturn(double expectedReturn) { this.expectedReturn = expectedReturn; }

    public List<WhatIfScenarioRow> getTableRows() { return tableRows; }
    public void setTableRows(List<WhatIfScenarioRow> tableRows) { this.tableRows = tableRows; }
}
