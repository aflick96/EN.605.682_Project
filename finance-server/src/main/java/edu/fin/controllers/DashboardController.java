package edu.fin.controllers;

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

    @GetMapping("/user/{userId}")
    public NetWorthBreakdown getDashboardData(@PathVariable Long userId) {   
        return dashboardService.getNetWorthBreakdown(userId);
    }
}
