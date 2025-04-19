package edu.fin.controllers;

import edu.fin.models.investment.*;
import edu.fin.repositories.investment.*;
import edu.fin.controllers.dtos.investment.InvestmentContributionRequest;
import edu.fin.controllers.dtos.investment.InvestmentLogRequest;
import edu.fin.controllers.dtos.investment.InvestmentSummary;
import edu.fin.services.InvestmentService;
import edu.fin.models.user.User;
import edu.fin.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private UserRepository userRepository;

	//Constructor
	public InvestmentController(InvestmentLogRepository log_repo, InvestmentContributionRepository cont_repo, InvestmentService service) {
		this.log_repo = log_repo;
		this.cont_repo = cont_repo;
		this.service = service;
	}
	
	// retrieve all investment logs for a user
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<InvestmentLog>> getInvestmentLogsByUser(@PathVariable Long userId) {
		List<InvestmentLog> logs = log_repo.findByUserId(userId);
		System.out.println("Investment logs for user " + userId + ": " + logs);
		return ResponseEntity.ok(logs);
	}

	// add a new investment log for a user
	@PostMapping("/user/{userId}")
	public ResponseEntity<String> createInvestmentLog(@PathVariable Long userId, @RequestBody InvestmentLogRequest req_log) {
		Optional<User> userOpt = userRepository.findById(userId); // get the user by id
		if (userOpt.isEmpty()) return ResponseEntity.badRequest().build(); // return bad request if user not found

		// create a new investment log entity and set its properties
		InvestmentLog log = new InvestmentLog();
		log.setUser(userOpt.get());
		log.setName(req_log.getName()); 
		log.setStartDate(req_log.getStartDate());
		log.setEndDate(req_log.getEndDate()); 
		log.setExpectedAnnualReturn(req_log.getExpectedAnnualReturn());

		// save the investment log to the database
		log_repo.save(log);
		return ResponseEntity.ok("Investment log created successfully");
	}

	// add a contribution to an investment log
	@PostMapping("contributions")
	public ResponseEntity<?> addNewContribution(@RequestBody InvestmentContributionRequest contribution) {
		// Get the investment log by id
		Optional<InvestmentLog> log_ = log_repo.findById(contribution.getInvestmentLogId());
		if (log_.isEmpty()) return ResponseEntity.badRequest().body("Investment log not found");
		InvestmentLog log = log_.get();

		// create a new investment contribution entity and set its properties
		InvestmentContribution cont = new InvestmentContribution();
		cont.setInvestmentLog(log);
		cont.setContributionDate(contribution.getContributionDate());
		cont.setContributionAmount(contribution.getContributionAmount());

		// save the investment contribution to the database
		cont_repo.save(cont);
		return ResponseEntity.ok("Investment contribution created successfully");
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
