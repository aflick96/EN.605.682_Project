package edu.fin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fin.dtos.expense.*;
import edu.fin.entities.expense.*;
import edu.fin.entities.expense.enums.ExpenseCategory;
import edu.fin.entities.expense.enums.ExpenseFrequency;
import edu.fin.entities.user.User;
import edu.fin.repositories.expense.*;
import edu.fin.repositories.user.UserRepository;

@Service
public class ExpenseService {
    
    @Autowired
    private ExpenseLogRepository expenseLogRepository;

    @Autowired
    private ExpenseItemRepository expenseItemRepository;

    @Autowired
    private UserRepository userRepository;

    public ExpenseLogRequest getExpenseLog(Long userId) {
        ExpenseLogRequest expenseLogRequest = null;
        List<ExpenseItemRequest> expenseItemRequest = null;
        List<ExpenseItem> expenseItems = null;

        ExpenseLog expenseLog = expenseLogRepository.findByUserId(userId).orElse(null);
        
        // If user does not have an expense log then create one
        if (expenseLog == null) {
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) return null;

            expenseLog = new ExpenseLog();
            expenseLog.setUser(user);
            expenseLog = expenseLogRepository.save(expenseLog);

            expenseLogRequest = new ExpenseLogRequest(expenseLog.getId(), null);
        } else {
            // If user has an expense log then get the items
            Long expenseLogId = expenseLog.getId();
            expenseItems = expenseItemRepository.findByExpenseLogId(expenseLogId);
            expenseItemRequest = expenseItems.stream().map(item -> {
                return new ExpenseItemRequest(
                    expenseLogId,
                    item.getId(),
                    item.getName(),
                    item.getCategory().toString(),
                    item.getFrequency().toString(),
                    item.getAmount(),
                    item.getStartDate(),
                    item.getEndDate()
                );
            }).toList();
            expenseLogRequest = new ExpenseLogRequest(expenseLogId, expenseItemRequest);
        }
        return expenseLogRequest;
    }

    public void createExpenseItem(Long userId, ExpenseItemRequest expenseItemRequest) {
        // Check if the user exists
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return;

        // Check if the expense log exists for the user
        ExpenseLog expenseLog = expenseLogRepository.findByUserId(user.getId()).orElse(null);
        if (expenseLog == null) return;
        
        // Create a new expense item and set its properties
        ExpenseItem expenseItem = new ExpenseItem();
        expenseItem.setName(expenseItemRequest.getName());
        expenseItem.setCategory(ExpenseCategory.valueOf(expenseItemRequest.getCategory()));
        expenseItem.setFrequency(ExpenseFrequency.valueOf(expenseItemRequest.getFrequency()));
        expenseItem.setAmount(expenseItemRequest.getAmount());
        expenseItem.setStartDate(expenseItemRequest.getStartDate());
        expenseItem.setEndDate(expenseItemRequest.getEndDate());
        
        // Set the expense log for the item and save it
        expenseItem.setExpenseLog(expenseLog);
        expenseItemRepository.save(expenseItem);
    }

    public void updateExpenseItem(Long userId, ExpenseItemRequest expenseItemRequest) {
        // Check if the user exists
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return;

        // Check if the expense log exists for the user
        ExpenseLog expenseLog = expenseLogRepository.findByUserId(user.getId()).orElse(null);
        if (expenseLog == null) return;
        
        // Find the existing expense item by ID
        ExpenseItem expenseItem = expenseItemRepository.findById(expenseItemRequest.getId()).orElse(null);
        if (expenseItem == null) return;

        // Update the properties of the existing expense item
        expenseItem.setName(expenseItemRequest.getName());
        expenseItem.setCategory(ExpenseCategory.valueOf(expenseItemRequest.getCategory()));
        expenseItem.setFrequency(ExpenseFrequency.valueOf(expenseItemRequest.getFrequency()));
        expenseItem.setAmount(expenseItemRequest.getAmount());
        expenseItem.setStartDate(expenseItemRequest.getStartDate());
        expenseItem.setEndDate(expenseItemRequest.getEndDate());

        // Set the expense log for the item and save it
        expenseItem.setExpenseLog(expenseLog);
        expenseItemRepository.save(expenseItem);
    }

    public void deleteExpenseItem(Long userId, Long itemId) {
        // Check if the user exists
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return;

        // Check if the expense log exists for the user
        ExpenseLog expenseLog = expenseLogRepository.findByUserId(user.getId()).orElse(null);
        if (expenseLog == null) return;
        
        // Find the existing expense item by ID
        ExpenseItem expenseItem = expenseItemRepository.findById(itemId).orElse(null);
        if (expenseItem == null) return;

        // Delete the expense item
        expenseItemRepository.delete(expenseItem);
    }
}
