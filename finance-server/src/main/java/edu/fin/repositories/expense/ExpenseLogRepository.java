package edu.fin.repositories.expense;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fin.entities.expense.ExpenseLog;

import java.util.Optional;

public interface ExpenseLogRepository extends JpaRepository<ExpenseLog, Long> {
	Optional<ExpenseLog> findByUserId(Long userId);
}
