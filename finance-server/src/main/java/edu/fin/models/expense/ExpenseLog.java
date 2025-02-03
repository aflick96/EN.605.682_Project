package edu.fin.models.expense;

import edu.fin.models.user.User;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="expense_logs")
public class ExpenseLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@OneToMany(mappedBy="expenseLog", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<ExpenseItem> items;

	public Long getExpenseLogId() {
		return id;
	}

	public void setExpenseLogId(Long expenseLogId) {
		this.id = expenseLogId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ExpenseItem> getItems() {
		return items;
	}

	public void setItems(List<ExpenseItem> items) {
		this.items = items;
	}
}
