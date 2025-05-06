/*
 * LoanPaymentsRequest.java
 * 
 * This class represents a request to add payments to a loan item. It contains the start date, end date, payment amount, payment day, specific day, and loan item ID.
 */
package edu.fin.dtos.loan;

public class LoanPaymentsRequest {
    private String startDate;
    private String endDate;
    private double paymentAmount;
    private String paymentDay; 
    private Integer specificDay;
    private Long loanItemId;

    // constructors
    public LoanPaymentsRequest() {}
    public LoanPaymentsRequest(String startDate, String endDate, double paymentAmount, String paymentDay, Integer specificDay, Long loanItemId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentAmount = paymentAmount;
        this.paymentDay = paymentDay;
        this.specificDay = specificDay;
        this.loanItemId = loanItemId;
    }

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