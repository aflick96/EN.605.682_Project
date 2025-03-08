package edu.fin.controllers;

import edu.fin.models.investment.*;
import edu.fin.repositories.investment.*;
import edu.fin.controllers.dtos.investment.InvestmentSummary;
import edu.fin.services.InvestmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {
	
	private final InvestmentLogRepository log_repo;
	private final InvestmentContributionRepository cont_repo;
	private final InvestmentService service;
	
	//Constructor
	public InvestmentController(
			InvestmentLogRepository log_repo, 
			InvestmentContributionRepository cont_repo,
			InvestmentService service
			) {
		this.log_repo = log_repo;
		this.cont_repo = cont_repo;
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<InvestmentLog> createInvestmentLog(@RequestBody InvestmentLog log) {
		InvestmentLog log_ = log_repo.save(log);
		return ResponseEntity.ok(log_);
	}
	
	@PostMapping("/{investmentId}/contributions")
	public ResponseEntity<?> addContribution(@PathVariable Long investmentLogId, @RequestBody InvestmentContribution contribution) {
		Optional<InvestmentLog> log_ = log_repo.findById(investmentLogId);
		if (log_.isEmpty()) {
			return ResponseEntity.badRequest().body("Investment log not found");
		}
		contribution.setInvestmentLog(log_.get());
		InvestmentContribution contribution_ = cont_repo.save(contribution);
		return ResponseEntity.ok(contribution_);
	}
	
	@GetMapping("/{investmentId}/summary")
	public ResponseEntity<?> getInvestmentSummary(@PathVariable Long investmentLogId) {
		Optional<InvestmentLog> log_ = log_repo.findById(investmentLogId);
		if (log_.isEmpty()) {
			return ResponseEntity.badRequest().body("Investment log not found");
		}
		
		InvestmentLog log = log_.get();
		List<InvestmentContribution> contributions = cont_repo.findByInvestmentLogId(investmentLogId);
		double currentValue = service.calculateInvestmentValue(log, contributions);
		double totalContributions = service.calculateTotalContributions(contributions);
		return ResponseEntity.ok(new InvestmentSummary(currentValue, totalContributions));
	}
}
