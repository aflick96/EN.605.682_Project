package edu.fin.repositories.expense;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import edu.fin.entities.expense.ExpenseItem;

public interface ExpenseItemRepository extends JpaRepository<ExpenseItem, Long> {
	List<ExpenseItem> findByExpenseLogId(Long expenseLogId);

	@Transactional
	void deleteByExpenseLogId(Long expenseLogId);
}
