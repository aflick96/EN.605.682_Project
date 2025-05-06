/*
 * LoanPaymentRequest.java
 * 
 * This class represents a request to make a payment on a loan. It contains an ID, the ID of the loan item, the date of the payment, and the amount of the payment.
 */
package edu.fin.dtos.loan;

import java.time.LocalDate;

public class LoanPaymentRequest {
    private Long id;
    private Long loanItemId;
    private LocalDate paymentDate;
    private Double paymentAmount;

    // constructors
    public LoanPaymentRequest() {}
    public LoanPaymentRequest(Long id, Long loanItemId, LocalDate paymentDate, Double paymentAmount) {
        this.id = id;
        this.loanItemId = loanItemId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLoanItemId() { return loanItemId; }
    public void setLoanItemId(Long loanItemId) { this.loanItemId = loanItemId; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public Double getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(Double paymentAmount) { this.paymentAmount = paymentAmount; }

    @Override
    public String toString() {
        return "LoanPaymentRequest{" +
                "id=" + id +
                "loanItemId=" + loanItemId +
                ", paymentDate=" + paymentDate +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
