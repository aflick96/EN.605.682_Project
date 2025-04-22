package edu.fin.entities.expense;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import edu.fin.entities.expense.enums.*;

@Entity
@Table(name="expense_items")
public class ExpenseItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
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
	@JsonBackReference
	private ExpenseLog expenseLog;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

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

	@Override
	public String toString() {
		return "ExpenseItem [id=" + id + ", name=" + name + ", category=" + category
				+ ", frequency=" + frequency + ", amount=" + amount + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
}
