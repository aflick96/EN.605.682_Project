package edu.fin.models.income;

import edu.fin.models.income.enums.PayFrequency;
import edu.fin.models.user.User;
import java.time.LocalDate;
import java.util.List;

public class IncomeLog {
    private Long incomeLogId;
    private User user;
    private Long userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double amount;
    private PayFrequency annualPayFrequency;
    private List<PretaxDeduction> pretaxDeductions;
    private List<PosttaxDeduction> posttaxDeductions;
    private List<AdditionalIncome> additionalIncomes;

    // default constructor
    public IncomeLog() {}

    // constructor
    public IncomeLog(Long incomeLogId, Long userId, LocalDate startDate, LocalDate endDate, PayFrequency annualPayFrequency) {
        this.incomeLogId = incomeLogId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.annualPayFrequency = annualPayFrequency;
    }

    // getters and setters
    public Long getIncomeLogId() { return incomeLogId; }
    public void setIncomeLogId(Long incomeLogId) { this.incomeLogId = incomeLogId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public PayFrequency getAnnualPayFrequency() { return annualPayFrequency; }
    public void setAnnualPayFrequency(PayFrequency annualPayFrequency) { this.annualPayFrequency = annualPayFrequency; }

    public List<PretaxDeduction> getPretaxDeductions() { return pretaxDeductions; }
    public void setPretaxDeductions(List<PretaxDeduction> pretaxDeductions) { this.pretaxDeductions = pretaxDeductions; }

    public List<PosttaxDeduction> getPosttaxDeductions() { return posttaxDeductions; }
    public void setPosttaxDeductions(List<PosttaxDeduction> posttaxDeductions) { this.posttaxDeductions = posttaxDeductions; }

    public List<AdditionalIncome> getAdditionalIncomes() { return additionalIncomes; }
    public void setAdditionalIncomes(List<AdditionalIncome> additionalIncomes) { this.additionalIncomes = additionalIncomes; }

    @Override
    public String toString() {
        return "IncomeLog{" +
               "incomeLogId=" + incomeLogId +
               ", userId=" + userId +
               ", startDate=" + startDate +
               ", endDate=" + endDate +
                ", amount=" + amount +
               ", annualPayFrequency=" + annualPayFrequency +
               '}';
    }
}
