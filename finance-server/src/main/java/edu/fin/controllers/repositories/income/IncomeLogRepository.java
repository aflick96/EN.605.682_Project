package edu.fin.controllers.repositories.income;

import edu.fin.models.income.IncomeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncomeLogRepository extends JpaRepository<IncomeLog, Long>{
	List<IncomeLog> findByUserId(Long userId);
}
