package edu.fin.controllers;

import edu.fin.dtos.investment.InvestmentContributionRequest;
import edu.fin.dtos.investment.InvestmentLogRequest;
import edu.fin.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {
	
	@Autowired
	private InvestmentService service;
	
	//Constructor
	public InvestmentController() {}
	
	// retrieve all investment logs for a user
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<InvestmentLogRequest>> getInvestmentLogsByUser(@PathVariable Long userId) {
		return ResponseEntity.ok(service.getInvestmentLogs(userId));		
	}

	// add a new investment log for a user
	@PostMapping("/user/{userId}")
	public void createInvestmentLog(@PathVariable Long userId, @RequestBody InvestmentLogRequest req_log) {
		service.createInvestmentLog(userId, req_log);
	}

	// add a contribution to an investment log
	@PostMapping("/user/{userId}/contribution")
	public void addNewContribution(@PathVariable Long userId, @RequestBody InvestmentContributionRequest contribution) {
		service.addContributionToInvestmentLog(userId, contribution);
	}

	// retrieve a specific investment log by ID
	@GetMapping("/user/{userId}/log/{logId}")
	public ResponseEntity<InvestmentLogRequest> getInvestmentLogById(@PathVariable Long userId, @PathVariable Long logId) {
		return ResponseEntity.ok(service.getInvestmentLogById(userId, logId));
	}

	// retrieve all contributions for a specific investment log
	@GetMapping("/user/{userId}/log/{logId}/contribution")
	public ResponseEntity<List<InvestmentContributionRequest>> getContributionsByLogId(@PathVariable Long userId, @PathVariable Long logId) {
		return ResponseEntity.ok(service.getInvestmentContributionsByLogId(userId, logId));
	}

	// delete a specific investment log by ID
	@DeleteMapping("/user/{userId}/log/{logId}")
	public void deleteInvestmentLogById(@PathVariable Long userId, @PathVariable Long logId) {
		service.deleteInvestmentLogById(userId, logId);
	}
}
