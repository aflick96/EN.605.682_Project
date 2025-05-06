/**
 * ExpenseController.java
 * 
 * This controller handles exposes endpoints for retrieving, creating, updating, and deleting expense items.
 */
package edu.fin.controllers;

import edu.fin.dtos.expense.ExpenseItemRequest;
import edu.fin.dtos.expense.ExpenseLogRequest;
import edu.fin.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
	
	@Autowired
	private ExpenseService service;
	
	public ExpenseController() {}
	
	/**
     * Retrieves the complete expense log for a user.
     *
     */
	@GetMapping("/user/{userId}")
	public ResponseEntity<ExpenseLogRequest> getExpenseLog(@PathVariable Long userId) {
		return ResponseEntity.ok(service.getExpenseLog(userId));		
	}

	/**
     * Creates a new expense item for a user.
     *
     */
	@PostMapping("/user/{userId}/items")
	public void createExpenseItem(@PathVariable Long userId, @RequestBody ExpenseItemRequest item) {
		service.createExpenseItem(userId, item);
	}
	
	/**
     * Updates an existing expense item for a user.
     *
     */
	@PutMapping("/user/{userId}/items")
	public void updateExpenseItem(@PathVariable Long userId, @RequestBody ExpenseItemRequest item) {
		service.updateExpenseItem(userId, item);
	}

	/**
     * Deletes an expense item for a user by item ID.
     *
     */
	@DeleteMapping("/user/{userId}/items/{itemId}")
	public void deleteExpenseItem(@PathVariable Long userId, @PathVariable Long itemId) {
		service.deleteExpenseItem(userId, itemId);
	}    
}
