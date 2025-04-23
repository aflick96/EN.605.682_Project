package edu.fin.controllers;

import edu.fin.dtos.*;
import edu.fin.dtos.dashboard.NetWorth;
import edu.fin.dtos.dashboard.NetWorthBreakdown;
import edu.fin.services.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
