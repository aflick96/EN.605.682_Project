/*
 * LoanCompletion.java
 * 
 * This class represents the loan completion data for a user. It contains a list of LoanCompletionData objects, which represent the completion data for each loan.
 */
package edu.fin.models.dashboard;

import java.util.List;

public class LoanCompletion {
    private List<LoanCompletionData> loanCompletionData;

    // constructors
    public LoanCompletion() {}
    public LoanCompletion(List<LoanCompletionData> loanCompletionData) {
        this.loanCompletionData = loanCompletionData;
    }

    // getters and setters
    public List<LoanCompletionData> getLoanCompletionData() { return loanCompletionData; }
    public void setLoanCompletionData(List<LoanCompletionData> loanCompletionData) { this.loanCompletionData = loanCompletionData; }

    @Override
    public String toString() {
        return "LoanCompletion{" +
                "loanCompletionData=" + loanCompletionData +
                '}';
    }
}
