package edu.fin.repositories.investment;

import edu.fin.models.investment.InvestmentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvestmentLogRepository extends JpaRepository<InvestmentLog, Long> {
	List<InvestmentLog> findByUserId(Long userId);
}
