package edu.fin.controllers;

import edu.fin.dtos.loan.*;
import edu.fin.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
	@Autowired
	private LoanService service;
	
	public LoanController() {}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<LoanItemRequest>> getLoanItemsForUser(@PathVariable Long userId) {
		return ResponseEntity.ok(service.getLoanItems(userId));
	}
	
	@PostMapping("/user/{userId}")
	public void createLoanItem(@PathVariable Long userId, @RequestBody LoanItemRequest item) {
		service.createLoanItem(userId, item);
	}
	
	@PostMapping("/user/{userId}/payment")
	public void addNewPayment(@PathVariable Long userId, @RequestBody LoanPaymentRequest payment) {
		service.addPaymentToLoanItem(userId, payment);
	}
}
