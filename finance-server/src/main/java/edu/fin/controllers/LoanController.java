package edu.fin.controllers;

import edu.fin.models.property.*;
import edu.fin.repositories.property.*;
import edu.fin.services.LoanService;
import edu.fin.controllers.dtos.property.LoanSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
	
	private final LoanItemRepository item_repo;
	private final LoanPaymentRepository pay_repo;
	private final LoanService service;
	
	public LoanController(LoanItemRepository item_repo, LoanPaymentRepository pay_repo, LoanService service) {
		this.item_repo = item_repo;
		this.pay_repo = pay_repo;
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<LoanItem> createLoanItem(@RequestBody LoanItem item) {
		LoanItem item_ = item_repo.save(item);
		return ResponseEntity.ok(item_);
	}
	
	@PostMapping("/{itemId}/payments")
	public ResponseEntity<?> addPayment(@PathVariable Long itemId, @RequestBody LoanPayment payment) {
		Optional<LoanItem> item_ = item_repo.findById(itemId);
		if (item_.isEmpty()) {
			return ResponseEntity.badRequest().body("Loan item not found");
		}
		payment.setLoanItem(item_.get());
		LoanPayment payment_ = pay_repo.save(payment);
		return ResponseEntity.ok(payment_);
	}
	
	@GetMapping("/{itemId}/summary")
	public ResponseEntity<?> getLoanSummary(@PathVariable Long itemId) {
		Optional<LoanItem> item_ = item_repo.findById(itemId);
		if (item_.isEmpty()) {
			return ResponseEntity.badRequest().body("Loan item not found");
		}
		
		LoanItem item = item_.get();
		List<LoanPayment> payments = pay_repo.findByLoanItemId(itemId);
		double remainingBalance = service.calculateRemainingBalance(item, payments);
		double totalInterestPaid = service.calculateTotalInterestPaid(payments);
		int adjustedLoanTerm = service.calculateAdjustedLoanTerm(item, payments);
		
		return ResponseEntity.ok(new LoanSummary(remainingBalance, totalInterestPaid, adjustedLoanTerm));
	}
}
