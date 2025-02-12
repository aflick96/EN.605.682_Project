package edu.fin.models.income;

public class PosttaxDeduction {
    private Long posttaxDeductionId;
    private Long incomeLogId;
    private String description;
    private double amount;

    // default constructor
    public PosttaxDeduction() {}

    // constructor
    public PosttaxDeduction(Long posttaxDeductionId, Long incomeLogId, String description, double amount) {
        this.posttaxDeductionId = posttaxDeductionId;
        this.incomeLogId = incomeLogId;
        this.description = description;
        this.amount = amount;
    }

    // getters and setters
    public Long getPosttaxDeductionId() { return posttaxDeductionId; }
    public void setPosttaxDeductionId(Long posttaxDeductionId) { this.posttaxDeductionId = posttaxDeductionId; }

    public Long getIncomeLogId() { return incomeLogId; }
    public void setIncomeLogId(Long incomeLogId) { this.incomeLogId = incomeLogId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
