/*
 * PretaxDeduction.java
 * 
 * This class represents a pretax deduction associated with an income log.
 */
package edu.fin.models.income;

public class PretaxDeduction {
    private Long pretaxDeductionId;
    private Long incomeLogId;
    private String description;
    private double amount;

    // constructors
    public PretaxDeduction() {}
    public PretaxDeduction(Long pretaxDeductionId, Long incomeLogId, String description, double amount) {
        this.pretaxDeductionId = pretaxDeductionId;
        this.incomeLogId = incomeLogId;
        this.description = description;
        this.amount = amount;
    }

    // getters and setters
    public Long getPretaxDeductionId() { return pretaxDeductionId; }
    public void setPretaxDeductionId(Long pretaxDeductionId) { this.pretaxDeductionId = pretaxDeductionId; }

    public Long getIncomeLogId() { return incomeLogId; }
    public void setIncomeLogId(Long incomeLogId) { this.incomeLogId = incomeLogId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
