package edu.fin.controllers;

import edu.fin.models.expense.*;
import edu.fin.repositories.expense.*;
import edu.fin.models.user.User;
import edu.fin.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
	
	private final ExpenseLogRepository log_repo;
	private final ExpenseItemRepository item_repo;
	private final UserService user_service;
	
	public ExpenseController(ExpenseLogRepository log_repo, ExpenseItemRepository item_repo, UserService user_service) {
		this.log_repo = log_repo;
		this.item_repo = item_repo;
		this.user_service = user_service;
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<ExpenseLog> getOrCreateExpenseLog(@PathVariable Long userId) {
		Optional<ExpenseLog> log_ = log_repo.findByUserId(userId);
		
		if (log_.isPresent()) {
			return ResponseEntity.ok(log_.get());
		} else {
			User user = user_service.getUserById(userId);
			if (user == null) return ResponseEntity.badRequest().body(null);
			ExpenseLog log = new ExpenseLog();
			log.setUser(user);
			log = log_repo.save(log);
			return ResponseEntity.ok(log);
		}
	}

	@GetMapping("/user/{userId}/items")
	public ResponseEntity<List<ExpenseItem>> getUserExpenseItems(@PathVariable Long userId) {
		Optional<ExpenseLog> log_ = log_repo.findByUserId(userId);
		if (log_.isEmpty()) return ResponseEntity.notFound().build();
		List<ExpenseItem> items = item_repo.findByExpenseLogId(log_.get().getExpenseLogId());
		System.out.println(items);
		return ResponseEntity.ok(items);
	}

	@PostMapping("/user/{userId}/items")
	public ResponseEntity<?> createExpenseItem(@PathVariable Long userId, @RequestBody ExpenseItem item) {
		Optional<ExpenseLog> log_ = log_repo.findByUserId(userId);
		if (log_.isEmpty()) return ResponseEntity.badRequest().body("Expense log not found");
		item.setExpenseLog(log_.get());
		ExpenseItem item_ = item_repo.save(item);
		return ResponseEntity.ok(item_);
	}
	
	@PutMapping("/items/{expenseItemId}")
	public ResponseEntity<?> updateExpenseItem(@PathVariable Long expenseItemId, @RequestBody ExpenseItem updateItem) {
		Optional<ExpenseItem> item_ = item_repo.findById(expenseItemId);
		if (item_.isEmpty()) return ResponseEntity.badRequest().body("Expense item not found");
		ExpenseItem item = item_.get();
		item.setName(updateItem.getName());
		item.setCategory(updateItem.getCategory());
		item.setFrequency(updateItem.getFrequency());
		item.setAmount(updateItem.getAmount());
		item.setStartDate(updateItem.getStartDate());
		item.setEndDate(updateItem.getEndDate());
		item_repo.save(item);
		return ResponseEntity.ok(item);
	}

	@DeleteMapping("/items/{expenseItemId}")
	public ResponseEntity<String> deleteExpenseItem(@PathVariable Long expenseItemId) {
		if (!item_repo.existsById(expenseItemId)) return ResponseEntity.badRequest().body("Expense item not found");
		item_repo.deleteById(expenseItemId);
		return ResponseEntity.ok("Expense item deleted");
	}    
}
