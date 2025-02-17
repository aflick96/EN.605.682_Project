package edu.fin.models.expense;

import edu.fin.models.user.User;
import java.util.List;

public class ExpenseLog {
    private Long id;
    private User user;
    private List<ExpenseItem> items;
 
    // getters and setters
	public Long getExpenseLogId() { return id; }
	public void setExpenseLogId(Long expenseLogId) { this.id = expenseLogId; }

	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

	public List<ExpenseItem> getItems() { return items; }
	public void setItems(List<ExpenseItem> items) { this.items = items; }
}