package edu.fin.models.expense;

import edu.fin.models.expense.enums.ExpenseCategory;
import java.time.LocalDate;

public class ExpenseItem {
    private Long expenseItemId;
    private String name;
    private ExpenseCategory category;
    private double amount;
    private LocalDate startDate;
    private LocalDate endDate;

	public Long getExpenseItemId() { return expenseItemId; }
	public void setExpenseItemId(Long expenseItemId) { this.expenseItemId = expenseItemId; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public ExpenseCategory getCategory() { return category; }
	public void setCategory(ExpenseCategory category) { this.category = category; }

	public Double getAmount() { return amount; }
	public void setAmount(Double amount) { this.amount = amount; }

	public LocalDate getStartDate() { return startDate; }
	public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

	public LocalDate getEndDate() { return endDate; }
	public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
