package edu.fin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fin.dtos.investment.InvestmentContributionRequest;
import edu.fin.dtos.investment.InvestmentLogRequest;
import edu.fin.entities.investment.*;
import edu.fin.entities.user.User;
import edu.fin.repositories.investment.InvestmentContributionRepository;
import edu.fin.repositories.investment.InvestmentLogRepository;
import edu.fin.repositories.user.UserRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class InvestmentService {

	@Autowired
	private InvestmentLogRepository investmentLogRepository;

	@Autowired 
	private InvestmentContributionRepository investmentContributionRepository;

	@Autowired
	private UserRepository userRepository;

	// Return the investment logs for a user
	public List<InvestmentLogRequest> getInvestmentLogs(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return null;

		List<InvestmentLog> logs = investmentLogRepository.findByUserId(userId);
		List<InvestmentLogRequest> req_logs = logs.stream().map(log -> {
			return new InvestmentLogRequest(
				log.getId(),
				log.getName(),
				log.getStartDate(),
				log.getEndDate(),
				log.getExpectedAnnualReturn()
			);
		}).toList();

		return req_logs;
	}

	// Create a new investment log for a user
	public void createInvestmentLog(Long userId, InvestmentLogRequest req_log) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return;

		InvestmentLog log = new InvestmentLog();
		log.setUser(user);
		log.setName(req_log.getName());
		log.setStartDate(req_log.getStartDate());
		log.setEndDate(req_log.getEndDate());
		log.setExpectedAnnualReturn(req_log.getExpectedAnnualReturn());

		investmentLogRepository.save(log);
	}

	// Create a new investment contribution for an investment log
	public void addContributionToInvestmentLog(Long userId, InvestmentContributionRequest req_contr) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return;

		InvestmentLog log = investmentLogRepository.findById(req_contr.getInvestmentLogId()).orElse(null);
		if (log == null) return;

		InvestmentContribution contribution = new InvestmentContribution();
		contribution.setInvestmentLog(log);
		contribution.setContributionDate(req_contr.getContributionDate());
		contribution.setContributionAmount(req_contr.getContributionAmount());

		investmentContributionRepository.save(contribution);
	}

	// Update a list of investment contributions for an investment log
	public void updateInvestmentContributions(Long userId, Long investmentLogId, List<InvestmentContributionRequest> contributions) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return;

		InvestmentLog log = investmentLogRepository.findById(investmentLogId).orElse(null);
		if (log == null) return;

		List<InvestmentContribution> existingContributions = investmentContributionRepository.findByInvestmentLogId(investmentLogId);
		for (InvestmentContribution existingContribution : existingContributions) {
			for (InvestmentContributionRequest contribution : contributions) {
				if (existingContribution.getId().equals(contribution.getId())) {
					existingContribution.setContributionDate(contribution.getContributionDate());
					existingContribution.setContributionAmount(contribution.getContributionAmount());
					investmentContributionRepository.save(existingContribution);
				}
			}
		}
	}

	// Get an investment log by ID for a user
	public InvestmentLogRequest getInvestmentLogById(Long userId, Long investmentLogId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return null;

		InvestmentLog log = investmentLogRepository.findById(investmentLogId).orElse(null);
		if (log == null) return null;

		return new InvestmentLogRequest(
			log.getId(),
			log.getName(),
			log.getStartDate(),
			log.getEndDate(),
			log.getExpectedAnnualReturn()
		);
	}

	// Get all contributions for an investment log by ID for a user
	public List<InvestmentContributionRequest> getInvestmentContributionsByLogId(Long userId, Long investmentLogId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return null;

		InvestmentLog log = investmentLogRepository.findById(investmentLogId).orElse(null);
		if (log == null) return null;

		List<InvestmentContribution> contributions = investmentContributionRepository.findByInvestmentLogId(investmentLogId);
		return contributions.stream().map(contribution -> {
			return new InvestmentContributionRequest(
				contribution.getId(),
				investmentLogId,
				contribution.getContributionDate(),
				contribution.getContributionAmount()
			);
		}).toList();
	}

	public void deleteInvestmentLogById(Long userId, Long investmentLogId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return;

		InvestmentLog log = investmentLogRepository.findById(investmentLogId).orElse(null);
		if (log == null) return;

		investmentLogRepository.delete(log);
	}

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
