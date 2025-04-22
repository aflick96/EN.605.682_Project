package edu.fin.repositories.investment;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fin.entities.investment.InvestmentLog;

import java.util.List;

public interface InvestmentLogRepository extends JpaRepository<InvestmentLog, Long> {
	List<InvestmentLog> findByUserId(Long userId);
}
