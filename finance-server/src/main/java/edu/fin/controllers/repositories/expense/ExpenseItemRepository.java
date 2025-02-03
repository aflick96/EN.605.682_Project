package edu.fin.controllers.repositories.expense;

import edu.fin.models.expense.ExpenseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseItemRepository extends JpaRepository<ExpenseItem, Long> {
	List<ExpenseItem> findByExpenseLogId(Long expenseLogId);
}
