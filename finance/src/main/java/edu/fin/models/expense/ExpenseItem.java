package edu.fin.models.expense;

import edu.fin.models.expense.enums.*;
import java.time.LocalDate;

public class ExpenseItem {
    private ExpenseLog expenseLog;
	private Long expenseItemId;
    private String name;
    private ExpenseCategory category;
	private ExpenseFrequency frequency;
    private double amount;
    private LocalDate startDate;
    private LocalDate endDate;

	public ExpenseLog getExpenseLog() { return expenseLog; }
	public void setExpenseLog(ExpenseLog expenseLog) { this.expenseLog = expenseLog; }

	public Long getExpenseItemId() { return expenseItemId; }
	public void setExpenseItemId(Long expenseItemId) { this.expenseItemId = expenseItemId; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public ExpenseCategory getCategory() { return category; }
	public void setCategory(ExpenseCategory category) { this.category = category; }

	public ExpenseFrequency getFrequency() { return frequency; }
	public void setFrequency(ExpenseFrequency frequency) { this.frequency = frequency; }

	public Double getAmount() { return amount; }
	public void setAmount(Double amount) { this.amount = amount; }

	public LocalDate getStartDate() { return startDate; }
	public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

	public LocalDate getEndDate() { return endDate; }
	public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

	@Override
	public String toString() {
		return "ExpenseItem{" +
				"expenseLog=" + expenseLog +
				", expenseItemId=" + expenseItemId +
				", name='" + name + '\'' +
				", category=" + category +
				", frequency=" + frequency +
				", amount=" + amount +
				", startDate=" + startDate +
				", endDate=" + endDate +
				'}';
	}
}
