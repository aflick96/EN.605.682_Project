/*
 * LoanPaymentsWrapper.java
 * 
 * This class is a wrapper for a list of LoanPayment objects.
 */

package edu.fin.models.loan;

import java.util.List;

public class LoanPaymentsWrapper {
    private List<LoanPayment> loanPayments;

    // constructors
    public LoanPaymentsWrapper() {}
    public List<LoanPayment> getLoanPayments() {
        return loanPayments;
    }

    // setter
    public void setLoanPayments(List<LoanPayment> loanPayments) {
        this.loanPayments = loanPayments;
    }
}
