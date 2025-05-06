/**
 * DashboardController.java
 * 
 * This controller handles display of users financial status on net-worth, income, expenses, and loan
 */
package edu.fin.controllers;

import edu.fin.dtos.dashboard.ExpenseByCategory;
import edu.fin.dtos.dashboard.LoanCompletion;
import edu.fin.dtos.dashboard.MonthlyCashFlow;
import edu.fin.dtos.dashboard.NetWorthBreakdown;
import edu.fin.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    public DashboardController() {}

    /**
     * Returns the net worth breakdown for a user.
     */
    @GetMapping("/user/{userId}/net-worth")
    public NetWorthBreakdown getDashboardData(@PathVariable Long userId) {   
        return dashboardService.getNetWorthBreakdown(userId);
    }

    /**
     * Returns a breakdown of expenses by category for a user.
     * 
     */
    @GetMapping("/user/{userId}/expense-by-category")
    public ExpenseByCategory getExpenseByCategories(@PathVariable Long userId) {   
        return dashboardService.getExpensesByCategory(userId);
    }

    /**
     * Returns the monthly cash flow (income vs. expenses) for a user.
     * 
     */
    @GetMapping("/user/{userId}/monthly-cash-flow")
    public MonthlyCashFlow getMonthlyCashFlow(@PathVariable Long userId) {   
        return dashboardService.getMonthlyCashFlow(userId);
    }

    /**
     * Returns the loan completion status for a user.
     * 
     */
    @GetMapping("/user/{userId}/loan-completion")
    public LoanCompletion getLoanCompletion(@PathVariable Long userId) {   
        return dashboardService.getLoanCompletions(userId);
    }
}
