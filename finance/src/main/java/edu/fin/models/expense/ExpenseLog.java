/*
 * ExpenseLog.java
 * 
 * This class represents a log of expenses for a user. It contains a list of expense items.
 */
package edu.fin.models.expense;

import java.util.List;

public class ExpenseLog {
    private Long id;
    private List<ExpenseItem> items;
 
	// constructors
	public ExpenseLog() {}
	public ExpenseLog(Long id, List<ExpenseItem> items) {
		this.id = id;
		this.items = items;
	}

    // getters and setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public List<ExpenseItem> getItems() { return items; }
	public void setItems(List<ExpenseItem> items) { this.items = items; }

	@Override
	public String toString() {
		return "ExpenseLog{" +
				"id=" + id +
				", items=" + items +
				'}';
	}
}