package edu.fin.repositories.expense;

import edu.fin.models.expense.ExpenseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ExpenseLogRepository extends JpaRepository<ExpenseLog, Long> {
	Optional<ExpenseLog> findByUserId(Long userId);
}
