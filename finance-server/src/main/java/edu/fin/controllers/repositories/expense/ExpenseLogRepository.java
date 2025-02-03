package edu.fin.controllers.repositories.expense;

import edu.fin.models.expense.ExpenseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseLogRepository extends JpaRepository<ExpenseLog, Long> {
	List<ExpenseLog> findByUserId(Long userId);
}
