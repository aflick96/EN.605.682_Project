package edu.fin.controllers;

import edu.fin.models.expense.*;
import edu.fin.controllers.repositories.expense.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
	
	private final ExpenseLogRepository log_repo;
	private final ExpenseItemRepository item_repo;
	
	public ExpenseController(ExpenseLogRepository log_repo, ExpenseItemRepository item_repo) {
		this.log_repo = log_repo;
		this.item_repo = item_repo;
	}
	
	@PostMapping
	public ResponseEntity<ExpenseLog> createExpenseLog(@RequestBody ExpenseLog log) {
		ExpenseLog log_ = log_repo.save(log);
		return ResponseEntity.ok(log_);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<ExpenseLog>> getExpenseLogByUser(@PathVariable Long userId) {
		List<ExpenseLog> logs = log_repo.findByUserId(userId);
		return ResponseEntity.ok(logs);
	}
	
    @PostMapping("/{expenseLogId}/items")
    public ResponseEntity<?> createExpenseItem(@PathVariable Long expenseLogId, @RequestBody ExpenseItem item) {
        Optional<ExpenseLog> log_ = log_repo.findById(expenseLogId);
        if (log_.isEmpty()) {
            return ResponseEntity.badRequest().body("Expense log not found.");
        }
        item.setExpenseLog(log_.get());
        ExpenseItem item_ = item_repo.save(item);
        return ResponseEntity.ok(item_);
    }
    
    @GetMapping("/{expenseLogId}/items")
    public ResponseEntity<List<ExpenseItem>> getExpenseItems(@PathVariable Long expenseLogId) {
    	List<ExpenseItem> items = item_repo.findByExpenseLogId(expenseLogId);
    	return ResponseEntity.ok(items);
    }
}
