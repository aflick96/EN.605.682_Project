package edu.fin.models.loan;

import java.util.List;

public class LoanPaymentsWrapper {
    private List<LoanPayment> loanPayments;

    public List<LoanPayment> getLoanPayments() {
        return loanPayments;
    }

    public void setLoanPayments(List<LoanPayment> loanPayments) {
        this.loanPayments = loanPayments;
    }
}
