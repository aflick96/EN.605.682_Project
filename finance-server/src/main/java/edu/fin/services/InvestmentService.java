package edu.fin.services;

import edu.fin.models.investment.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class InvestmentService {
	public double calculateInvestmentValue(InvestmentLog log, List<InvestmentContribution> contributions) {
		double totalValue = 0.0;
		double annualRate = log.getExpectedAnnualReturn() / 100;
		LocalDate endDate = log.getEndDate() != null ? log.getEndDate() : LocalDate.now();
		
		for (InvestmentContribution contribution : contributions) {
			long years = ChronoUnit.DAYS.between(contribution.getContributionDate(), endDate) / 365;
			totalValue += contribution.getContributionAmount() * Math.pow(1 + annualRate, years);
		}
		
		return totalValue;
	}
	
	public double calculateTotalContributions(List<InvestmentContribution> contributions) {
        return contributions.stream()
                .mapToDouble(InvestmentContribution::getContributionAmount)
                .sum();
	}
}
