/*
 * ExpenseLog.java
 * 
 * This class represents an expense log. It contains a unique ID, a user associated with the log, and a list of expense items.
 */
package edu.fin.entities.expense;

import edu.fin.entities.user.User;
import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="expense_logs")
public class ExpenseLog {
	
	//fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@OneToMany(mappedBy="expenseLog", cascade=CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference
	private List<ExpenseItem> items;

	// getters and setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

	public List<ExpenseItem> getItems() { return items; }
	public void setItems(List<ExpenseItem> items) { this.items = items; }

	@Override
	public String toString() {
		return "ExpenseLog [id=" + id + ", items=" + items + ", user=" + user + "]";
	}
}
