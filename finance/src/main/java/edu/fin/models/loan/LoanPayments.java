/*
 * LoanPayments.java
 * 
 * This class represents the payment details for a loan item.
 */
package edu.fin.models.loan;

public class LoanPayments {
    private String startDate;
    private String endDate;
    private double paymentAmount;
    private String paymentDay; 
    private Integer specificDay;
    private Long loanItemId;

    // constructors
    public LoanPayments() {}
    public LoanPayments(String startDate, String endDate, double paymentAmount, String paymentDay, Integer specificDay, Long loanItemId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentAmount = paymentAmount;
        this.paymentDay = paymentDay;
        this.specificDay = specificDay;
        this.loanItemId = loanItemId;
    }

    // getters and setters
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public double getPaymentAmount() { return paymentAmount; }
    public void setPaymentAmount(double paymentAmount) { this.paymentAmount = paymentAmount; }

    public String getPaymentDay() { return paymentDay; }
    public void setPaymentDay(String paymentDay) { this.paymentDay = paymentDay; }

    public Integer getSpecificDay() { return specificDay; }
    public void setSpecificDay(Integer specificDay) { this.specificDay = specificDay; }

    public Long getLoanItemId() { return loanItemId; }
    public void setLoanItemId(Long loanItemId) { this.loanItemId = loanItemId;}

    @Override
    public String toString() {
        return "LoanPayments{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", paymentAmount=" + paymentAmount +
                ", paymentDay='" + paymentDay + '\'' +
                ", specificDay=" + specificDay +
                ", loanItemId=" + loanItemId +
                '}';
    }
}
