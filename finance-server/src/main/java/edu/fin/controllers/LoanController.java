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
	
	//retrieve a specific loan item by ID
	@GetMapping("/user/{userId}/item/{itemId}")
	public ResponseEntity<LoanItemRequest> getLoanItemById(@PathVariable Long userId, @PathVariable Long itemId) {
		return ResponseEntity.ok(service.getLoanItemById(userId, itemId));
	}

	//delete a specific loan item by ID
	@DeleteMapping("/user/{userId}/item/{itemId}")
	public void deleteLoanItemById(@PathVariable Long userId, @PathVariable Long itemId) {
		service.deleteLoanItemById(userId, itemId);
	}

	//retrieve all payments for a specific loan item
	@GetMapping("/user/{userId}/item/{itemId}/payment")
	public ResponseEntity<List<LoanPaymentRequest>> getPaymentsByItemId(@PathVariable Long userId, @PathVariable Long itemId) {
		return ResponseEntity.ok(service.getLoanPaymentsByItemId(userId, itemId));
	}

	// Add a new payment to a loan item
	@PostMapping("/user/{userId}/item/{itemId}/payment")
	public void addNewPayment(@PathVariable Long userId, @PathVariable Long itemId, @RequestBody LoanPaymentRequest payment) {
		service.addPaymentToLoanItem(userId, itemId, payment);
	}

	@PostMapping("user/{userId}/item/{itemId}/payments")
	public void addPayments(@PathVariable Long userId, @PathVariable Long itemId, @RequestBody LoanPaymentsRequest payments) {
		service.addPaymentsToLoanItem(userId, itemId, payments);
	}

	//update payments for a specific loan item
	@PutMapping("/user/{userId}/item/{itemId}/payment")
	public void updatePayments(@PathVariable Long userId, @PathVariable Long itemId, @RequestBody List<LoanPaymentRequest> payments) {
		service.updateLoanPayments(userId, itemId, payments);
	}
}
