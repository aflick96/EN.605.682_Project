package edu.fin.models.expense;

import java.util.List;

public class ExpenseLog {
    private Long id;
    private List<ExpenseItem> items;
 
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