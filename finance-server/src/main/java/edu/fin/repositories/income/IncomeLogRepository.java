package edu.fin.repositories.income;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fin.entities.income.IncomeLog;

import java.util.List;

public interface IncomeLogRepository extends JpaRepository<IncomeLog, Long>{
	List<IncomeLog> findByUserId(Long userId);
}
