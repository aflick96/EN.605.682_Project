/*
 * IncomeByPayFrequencyDetail.java
 * 
 * This class represents the details of income by pay frequency for a user.
 */
package edu.fin.models.income;

import java.time.LocalDate;

public class IncomeByPayFrequencyDetail {
    private LocalDate payDate;
    private Double grossIncome;
    private Double additionalIncome;
    private Double pretaxDeductions;
    private Double taxableIncome;
    private Double federalTax;
    private Double stateTax;
    private Double posttaxDeductions;
    private Double netIncome;

    //constructors
    public IncomeByPayFrequencyDetail() {}
    public IncomeByPayFrequencyDetail(LocalDate payDate, Double grossIncome, Double additionalIncome, Double pretaxDeductions, Double taxableIncome, Double federalTax, Double stateTax, Double posttaxDeductions, Double netIncome) {
        this.payDate = payDate;
        this.grossIncome = grossIncome;
        this.additionalIncome = additionalIncome;
        this.pretaxDeductions = pretaxDeductions;
        this.taxableIncome = taxableIncome;
        this.federalTax = federalTax;
        this.stateTax = stateTax;
        this.posttaxDeductions = posttaxDeductions;
        this.netIncome = netIncome;
    }

    //Getters and Setters
    public LocalDate getPayDate() { return payDate; }
    public void setPayDate(LocalDate payDate) { this.payDate = payDate; }

    public Double getGrossIncome() { return grossIncome; }
    public void setGrossIncome(Double grossIncome) { this.grossIncome = grossIncome; }

    public Double getAdditionalIncome() { return additionalIncome; }
    public void setAdditionalIncome(Double additionalIncome) { this.additionalIncome = additionalIncome; }

    public Double getPretaxDeductions() { return pretaxDeductions; }
    public void setPretaxDeductions(Double pretaxDeductions) { this.pretaxDeductions = pretaxDeductions; }

    public Double getTaxableIncome() { return taxableIncome; }
    public void setTaxableIncome(Double taxableIncome) { this.taxableIncome = taxableIncome; }

    public Double getFederalTax() { return federalTax; }
    public void setFederalTax(Double federalTax) { this.federalTax = federalTax; }

    public Double getStateTax() { return stateTax; }
    public void setStateTax(Double stateTax) { this.stateTax = stateTax; }

    public Double getPosttaxDeductions() { return posttaxDeductions; }
    public void setPosttaxDeductions(Double posttaxDeductions) { this.posttaxDeductions = posttaxDeductions; }

    public Double getNetIncome() { return netIncome; }
    public void setNetIncome(Double netIncome) { this.netIncome = netIncome; }

    @Override
    public String toString() {
        return "IncomeByPayFrequencyDetail{" +
                "payDate=" + payDate +
                ", grossIncome=" + grossIncome +
                ", additionalIncome=" + additionalIncome +
                ", pretaxDeductions=" + pretaxDeductions +
                ", taxableIncome=" + taxableIncome +
                ", federalTax=" + federalTax +
                ", stateTax=" + stateTax +
                ", posttaxDeductions=" + posttaxDeductions +
                ", netIncome=" + netIncome +
                '}';
    }
}
