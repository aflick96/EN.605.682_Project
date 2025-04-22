package edu.fin.models.loan;

import java.util.List;

public class LoanScenarioResult {
    private double scenarioPayment;
    private List<WhatIfScenarioRow> tableRows;

    public LoanScenarioResult(double scenarioPayment, List<WhatIfScenarioRow> tableRows) {
        this.scenarioPayment = scenarioPayment;
        this.tableRows = tableRows;
    }

    public double getScenarioPayment() { return scenarioPayment; }
    public void setScenarioPayment(double scenarioPayment) { this.scenarioPayment = scenarioPayment; }

    public List<WhatIfScenarioRow> getTableRows() { return tableRows; }
    public void setTableRows(List<WhatIfScenarioRow> tableRows) { this.tableRows = tableRows;}
}
