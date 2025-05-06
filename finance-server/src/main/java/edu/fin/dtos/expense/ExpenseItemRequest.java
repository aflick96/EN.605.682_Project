/*
 * ExpenseItemRequest.java
 * 
 * This class represents a request to create or update an expense item. It contains fields for the expense log ID, item ID, name, category, frequency, amount, start date, and end date.
 */
package edu.fin.dtos.expense;

import java.time.LocalDate;

public class ExpenseItemRequest {
    private Long expenseLogId;
    private Long id;
    private String name;
    private String category;
    private String frequency;
    private double amount;
    private LocalDate startDate;
    private LocalDate endDate;

    // constructors
    public ExpenseItemRequest() {}
    public ExpenseItemRequest(Long expenseLogId, Long id, String name, String category, String frequency, double amount, LocalDate startDate, LocalDate endDate) {
        this.expenseLogId = expenseLogId;
        this.id = id;
        this.name = name;
        this.category = category;
        this.frequency = frequency;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // getters and setters
    public Long getExpenseLogId() { return expenseLogId; }
    public void setExpenseLogId(Long expenseLogId) { this.expenseLogId = expenseLogId; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    @Override
    public String toString() {
        return "ExpenseItemRequest [expenseLogId=" + expenseLogId + ", id=" + id + ", name=" + name + ", category=" + category
                + ", frequency=" + frequency + ", amount=" + amount + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }
}
