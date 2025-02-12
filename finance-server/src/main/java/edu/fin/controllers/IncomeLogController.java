package edu.fin.controllers;

import edu.fin.models.income.IncomeLog;
import edu.fin.models.income.AdditionalIncome;
import edu.fin.models.income.PretaxDeduction;
import edu.fin.models.income.PosttaxDeduction;
import edu.fin.controllers.repositories.income.IncomeLogRepository;
import edu.fin.controllers.repositories.income.AdditionalIncomeRepository;
import edu.fin.controllers.repositories.income.PosttaxDeductionRepository;
import edu.fin.controllers.repositories.income.PretaxDeductionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/income-logs")
public class IncomeLogController {
	
	private final IncomeLogRepository inc_repo;
	private final AdditionalIncomeRepository add_repo;
	private final PretaxDeductionRepository pre_repo;
	private final PosttaxDeductionRepository post_repo;
	
	//Constructor
	public IncomeLogController(
			IncomeLogRepository inc_repo, 
			AdditionalIncomeRepository add_repo, 
			PretaxDeductionRepository pre_repo, 
			PosttaxDeductionRepository post_repo
			) {
		this.inc_repo = inc_repo;
		this.add_repo = add_repo;
		this.pre_repo = pre_repo;
		this.post_repo = post_repo;
	}
	
	/* Income logs */
	//########################################################################################
	//GET by user id
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<IncomeLog>> getIncomeLogsByUser(@PathVariable Long userId) {
		List<IncomeLog> logs = inc_repo.findByUserId(userId);
		return ResponseEntity.ok(logs);
	}
	
	//POST income log for user
	@PostMapping
	public ResponseEntity<IncomeLog> createIncomeLog(@RequestBody IncomeLog log) {
		System.out.println(log);
		if (log.getAmount() == null || log.getAmount() < 0) {
			return ResponseEntity.badRequest().body(null);
		}
		IncomeLog log_ = inc_repo.save(log);
		return ResponseEntity.ok(log_);
	}

	//DELETE income log for user
	@DeleteMapping("/{incomeLogId}")
	public ResponseEntity<String> deleteIncomeLog(@PathVariable Long incomeLogId) {
		if (!inc_repo.existsById(incomeLogId)) {
			return ResponseEntity.badRequest().body("Income log not found");
		}
		add_repo.deleteByIncomeLogId(incomeLogId);
		pre_repo.deleteByIncomeLogId(incomeLogId);
		post_repo.deleteByIncomeLogId(incomeLogId);
		inc_repo.deleteById(incomeLogId);
		return ResponseEntity.ok("Income log deleted");
	}
	//########################################################################################

	/* Additional incomes */
	
	// Create additional income for income log for user
	@PostMapping("/{incomeLogId}/additional-income")
	public ResponseEntity<?> createAdditionalIncome(@PathVariable Long incomeLogId, @RequestBody AdditionalIncome add) {
		Optional<IncomeLog> incomeLog = inc_repo.findById(incomeLogId);
		if (incomeLog.isEmpty()) {
			return ResponseEntity.badRequest().body("Income log not found");
		}
		add.setIncomeLog(incomeLog.get());
		AdditionalIncome add_ = add_repo.save(add);
		return ResponseEntity.ok(add_);
	}
	
	// Get additional income for income log for user
	@GetMapping("/{incomeLogId}/additional-income")
	public ResponseEntity<List<AdditionalIncome>> getAdditionalIncome(@PathVariable Long incomeLogId) {
		List<AdditionalIncome> incomes = add_repo.findByIncomeLogId(incomeLogId);
		return ResponseEntity.ok(incomes);
	}
	
	/* Pretax deductions */
	
	// Create pretax deduction for income log for user
	@PostMapping("/{incomeLogId}/pretax")
	public ResponseEntity<?> createPretaxDeduction(@PathVariable Long incomeLogId, @RequestBody PretaxDeduction pre) {
		Optional<IncomeLog> incomeLog = inc_repo.findById(incomeLogId);
		if (incomeLog.isEmpty()) {
			return ResponseEntity.badRequest().body("Income log not found");
		}
		pre.setIncomeLog(incomeLog.get());
		PretaxDeduction pre_ = pre_repo.save(pre);
		return ResponseEntity.ok(pre_);
	}
	
	// Get pretax deduction for income log for user
	@GetMapping("/{incomeLogId}/pretax")
	public ResponseEntity<List<PretaxDeduction>> getPretaxDeductions(@PathVariable Long incomeLogId) {
		List<PretaxDeduction> deds = pre_repo.findByIncomeLogId(incomeLogId);
		return ResponseEntity.ok(deds);
	}

	/* Posttax deductions */
	
	// Create posttax deduction for income log for user
	@PostMapping("/{incomeLogId}/posttax")
	public ResponseEntity<?> createPosttaxDeduction(@PathVariable Long incomeLogId, @RequestBody PosttaxDeduction post) {
		Optional<IncomeLog> incomeLog = inc_repo.findById(incomeLogId);
		if (incomeLog.isEmpty()) {
			return ResponseEntity.badRequest().body("Income log not found");
		}
		post.setIncomeLog(incomeLog.get());
		PosttaxDeduction post_ = post_repo.save(post);
		return ResponseEntity.ok(post_);
	}
	
	// Get posttax deduction for income log for user
	@GetMapping("/{incomeLogId}/posttax")
	public ResponseEntity<List<PosttaxDeduction>> getPosttaxDeductions(@PathVariable Long incomeLogId) {
		List<PosttaxDeduction> deds = post_repo.findByIncomeLogId(incomeLogId);
		return ResponseEntity.ok(deds);
	}
}
