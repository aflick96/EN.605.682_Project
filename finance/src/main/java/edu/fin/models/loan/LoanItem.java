/*
 * LoanItem.java
 * 
 * This class represents a loan item with its details such as name, item value, loan amount,
 * interest rate, start date, and loan term in months.
 */
package edu.fin.models.loan;

import java.time.LocalDate;

public class LoanItem {

    private Long id;
    private String name;
    private Double itemValue;
    private Double loanAmount;
    private Double interestRate;
    private LocalDate startDate;
    private Integer loanTermMonths;

    // Constructors
    public LoanItem() {}
    public LoanItem(Long id, String name, Double itemValue, Double loanAmount, Double interestRate, LocalDate startDate, Integer loanTermMonths) {
        this.id = id;
        this.name = name;
        this.itemValue = itemValue;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.loanTermMonths = loanTermMonths;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getItemValue() { return itemValue; }
    public void setItemValue(Double itemValue) { this.itemValue = itemValue; }

    public Double getLoanAmount() { return loanAmount;}
    public void setLoanAmount(Double loanAmount) {this.loanAmount = loanAmount; }

    public Double getInterestRate() {return interestRate;}
    public void setInterestRate(Double interestRate) {this.interestRate = interestRate;}

    public LocalDate getStartDate() {return startDate;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

    public Integer getLoanTermMonths() {return loanTermMonths;}
    public void setLoanTermMonths(Integer loanTermMonths) {this.loanTermMonths = loanTermMonths;}

    @Override
    public String toString() {
        return "LoanItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", itemValue=" + itemValue +
                ", loanAmount=" + loanAmount +
                ", interestRate=" + interestRate +
                ", startDate='" + startDate + '\'' +
                ", loanTermMonths=" + loanTermMonths +
                '}';
    }
}
