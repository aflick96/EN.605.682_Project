package edu.fin.models.loan;

import java.time.LocalDate;

public class LoanPayment {
    private Long loanItemId;
    private LocalDate paymentDate;
    private double paymentAmount;

    public Long getLoanItemId() { return loanItemId; }
    public void setLoanItemId(Long loanItemId) { this.loanItemId = loanItemId; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    
    public double getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(double paymentAmount) { this.paymentAmount = paymentAmount; }

    @Override
    public String toString() {
        return "LoanPayment{" +
                "loanItemId=" + loanItemId +
                ", paymentDate=" + paymentDate +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
