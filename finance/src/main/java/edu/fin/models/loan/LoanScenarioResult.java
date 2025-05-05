/*
 * LoanScenarioResult.java
 * 
 * This class represents the result of a loan scenario calculation.
 */
package edu.fin.models.loan;

import java.util.List;

public class LoanScenarioResult {
    private double scenarioPayment;
    private int loanTerms;
    private List<WhatIfScenarioRow> tableRows;

    // constructors
    public LoanScenarioResult() {}
    public LoanScenarioResult(double scenarioPayment, int loanTerms, List<WhatIfScenarioRow> tableRows) {
        this.scenarioPayment = scenarioPayment;
        this.loanTerms = loanTerms;
        this.tableRows = tableRows;
    }

    // getters and setters
    public double getScenarioPayment() { return scenarioPayment; }
    public void setScenarioPayment(double scenarioPayment) { this.scenarioPayment = scenarioPayment; }

    public int getLoanTerms() { return loanTerms; }
    public void setLoanTerms(int loanTerms) { this.loanTerms = loanTerms; }

    public List<WhatIfScenarioRow> getTableRows() { return tableRows; }
    public void setTableRows(List<WhatIfScenarioRow> tableRows) { this.tableRows = tableRows;}
}
