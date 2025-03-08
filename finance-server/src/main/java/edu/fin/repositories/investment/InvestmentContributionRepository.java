package edu.fin.repositories.investment;

import edu.fin.models.investment.InvestmentContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvestmentContributionRepository extends JpaRepository<InvestmentContribution, Long> {
	List<InvestmentContribution> findByInvestmentLogId(Long investmentLogId);
}