/*
 * MonthlyCashFlow.java
 * 
 * This class represents the monthly cash flow data for a user. It contains a list of MonthlyCashFlowData objects, which represent the cash flow data for each month.
 */

package edu.fin.dtos.dashboard;

import java.util.List;

public class MonthlyCashFlow {
    private List<MonthlyCashFlowData> cashFlowData;

    // constructors
    public MonthlyCashFlow() {}
    public MonthlyCashFlow(List<MonthlyCashFlowData> cashFlowData) {
        this.cashFlowData = cashFlowData;
    }

    // getters and setters
    public List<MonthlyCashFlowData> getCashFlowData() { return cashFlowData; }
    public void setCashFlowData(List<MonthlyCashFlowData> cashFlowData) { this.cashFlowData = cashFlowData; }

    @Override
    public String toString() {
        return "MonthlyCashFlow{" +
                "cashFlowData=" + cashFlowData +
                '}';
    }
}
