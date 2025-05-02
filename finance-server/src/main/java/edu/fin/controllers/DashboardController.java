package edu.fin.controllers;

import edu.fin.dtos.dashboard.ExpenseByCategory;
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

    @GetMapping("/user/{userId}/net-worth")
    public NetWorthBreakdown getDashboardData(@PathVariable Long userId) {   
        return dashboardService.getNetWorthBreakdown(userId);
    }

    @GetMapping("/user/{userId}/expense-by-category")
    public ExpenseByCategory getExpenseByCategories(@PathVariable Long userId) {   
        return dashboardService.getExpensesByCategory(userId);
    }
}
