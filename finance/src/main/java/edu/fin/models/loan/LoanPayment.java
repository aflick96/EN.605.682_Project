/*
 * LoanPayment.java
 * 
 * This class represents a loan payment made by a user.
 */
package edu.fin.models.loan;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class LoanPayment {
    private Long id;
    private Long loanItemId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    private double paymentAmount;

    // constructors
    public LoanPayment() {}
    public LoanPayment(Long id, Long loanItemId, LocalDate paymentDate, double paymentAmount) {
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
    
    public double getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(double paymentAmount) { this.paymentAmount = paymentAmount; }

    @Override
    public String toString() {
        return "LoanPayment{" +
                "id=" + id +
                ", loanItemId=" + loanItemId +
                ", paymentDate=" + paymentDate +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
