package edu.fin.repositories.investment;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fin.entities.investment.InvestmentContribution;

import java.util.List;

public interface InvestmentContributionRepository extends JpaRepository<InvestmentContribution, Long> {
	List<InvestmentContribution> findByInvestmentLogId(Long investmentLogId);
}