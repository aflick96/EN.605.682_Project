package edu.fin.models.income;

public class AdditionalIncome {
    private Long additionalIncomeId;
    private Long incomeLogId;
    private String description;
    private double amount;

    // default constructor
    public AdditionalIncome() {}

    // constructor
    public AdditionalIncome(Long additionalIncomeId, Long incomeLogId, String description, double amount) {
        this.additionalIncomeId = additionalIncomeId;
        this.incomeLogId = incomeLogId;
        this.description = description;
        this.amount = amount;
    }

    // getters and setters
    public Long getAdditionalIncomeId() { return additionalIncomeId; }
    public void setAdditionalIncomeId(Long additionalIncomeId) { this.additionalIncomeId = additionalIncomeId; }

    public Long getIncomeLogId() { return incomeLogId; }
    public void setIncomeLogId(Long incomeLogId) { this.incomeLogId = incomeLogId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
