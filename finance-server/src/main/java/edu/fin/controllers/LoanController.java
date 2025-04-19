package edu.fin.controllers;

import edu.fin.models.user.User;
import edu.fin.repositories.user.UserRepository;
import edu.fin.models.property.*;
import edu.fin.repositories.property.*;
import edu.fin.controllers.dtos.property.*;
import edu.fin.services.LoanService;
import edu.fin.controllers.dtos.property.LoanSummary;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private UserRepository userRepository;
	
	public LoanController(LoanItemRepository item_repo, LoanPaymentRepository pay_repo, LoanService service) {
		this.item_repo = item_repo;
		this.pay_repo = pay_repo;
		this.service = service;
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<LoanItem>> getLoanItemsForUser(@PathVariable Long userId) {
		List<LoanItem> items = item_repo.findByUserId(userId);
		return ResponseEntity.ok(items);
	}
	
	@PostMapping("/user/{userId}")
	public ResponseEntity<LoanItem> createLoanItem(@PathVariable Long userId, @RequestBody LoanItem item) {
		Optional<User> userOpt = userRepository.findById(userId);
		if (userOpt.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
	
		item.setUser(userOpt.get());
		LoanItem saved = item_repo.save(item);
		return ResponseEntity.ok(saved);
	}
	
	@PostMapping("payments")
	public ResponseEntity<?> addNewPayment(@RequestBody LoanPaymentRequest payment) {
		// Get the loan item by id
		Optional<LoanItem> item_ = item_repo.findById(payment.getLoanItemId());
		if (item_.isEmpty()) return ResponseEntity.badRequest().body("Loan item not found");
		LoanItem loanItem = item_.get();

		// create a new loan payment entity and set its properties
		LoanPayment loanPayment = new LoanPayment();
		loanPayment.setLoanItem(loanItem);
		loanPayment.setPaymentDate(payment.getPaymentDate());
		loanPayment.setPaymentAmount(payment.getPaymentAmount());

		// populate the payment breakdown - this will calculate the interest and principal paid
		List<LoanPayment> previousPayments = pay_repo.findByLoanItemIdOrderByPaymentDateAsc(loanItem.getId());
		service.populatePaymentBreakdown(loanItem, previousPayments, loanPayment);

		// save the payment to the database
		pay_repo.save(loanPayment);
		return ResponseEntity.ok("Payment added successfully");
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
