package edu.fin.dtos.loan;

import java.time.LocalDate;

public class LoanPaymentRequest {
    private Long loanItemId;
    private LocalDate paymentDate;
    private Double paymentAmount;

    public LoanPaymentRequest() {}
    public LoanPaymentRequest(Long loanItemId, LocalDate paymentDate, Double paymentAmount) {
        this.loanItemId = loanItemId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    public Long getLoanItemId() { return loanItemId; }
    public void setLoanItemId(Long loanItemId) { this.loanItemId = loanItemId; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public Double getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(Double paymentAmount) { this.paymentAmount = paymentAmount; }

    @Override
    public String toString() {
        return "LoanPaymentRequest{" +
                "loanItemId=" + loanItemId +
                ", paymentDate=" + paymentDate +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
