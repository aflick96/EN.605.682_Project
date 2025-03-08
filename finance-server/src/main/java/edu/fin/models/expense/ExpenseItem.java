package edu.fin.models.expense;

import edu.fin.models.expense.enums.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="expense_items")
public class ExpenseItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long expenseItemId;
	
	@Column(nullable=false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private ExpenseCategory category;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private ExpenseFrequency frequency;

	@Column(nullable=false)
	private Double amount;
	
	@Column(name="start_date", nullable=false)
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@ManyToOne
	@JoinColumn(name="expense_log_id", nullable=false)
	private ExpenseLog expenseLog;

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

	public ExpenseLog getExpenseLog() {	return expenseLog; }
	public void setExpenseLog(ExpenseLog expenseLog) { this.expenseLog = expenseLog; }
}
