/*
 * DashboardController.java
 * 
 * This class handles the dashboard functionality for the application.
 */
package edu.fin.controllers;

import edu.fin.models.user.User;
import edu.fin.config.APIConfig;
import edu.fin.models.dashboard.NetWorthBreakdown;
import edu.fin.models.dashboard.ExpenseByCategory;
import edu.fin.models.dashboard.LoanCompletion;
import edu.fin.models.dashboard.MonthlyCashFlow;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AuthenticatedController {
    @Autowired
    private APIConfig ac;

    @Autowired
    private RestTemplate rt;

    public DashboardController() {}

    /**
     * Handles GET requests to the dashboard.
     * Retrieves all dashboard data for the authenticated user and loads the view.
     */
    @GetMapping
    public String showDashboard(Model model, HttpSession session) {
        Long userId = requireUserId(session);

        String userUrl = ac.getBaseUrl() + "/users/" + userId;
        String dashboardUrl = ac.getBaseUrl() + "/dashboard/user/" + userId + "/net-worth";
        String expenseByCategoryUrl = ac.getBaseUrl() + "/dashboard/user/" + userId + "/expense-by-category";
        String monthlyCashFlowUrl = ac.getBaseUrl() + "/dashboard/user/" + userId + "/monthly-cash-flow";
        String loanCompletionUrl = ac.getBaseUrl() + "/dashboard/user/" + userId + "/loan-completion";

        User user = rt.getForObject(userUrl, User.class);
        NetWorthBreakdown netWorthBreakdown = rt.getForObject(dashboardUrl, NetWorthBreakdown.class); 
        ExpenseByCategory expenseByCategory = rt.getForObject(expenseByCategoryUrl, ExpenseByCategory.class);
        MonthlyCashFlow monthlyCashFlow = rt.getForObject(monthlyCashFlowUrl, MonthlyCashFlow.class);
        LoanCompletion loanCompletion = rt.getForObject(loanCompletionUrl, LoanCompletion.class);

        model.addAttribute("user", user);
        model.addAttribute("breakdown", netWorthBreakdown);
        model.addAttribute("expenseByCategory", expenseByCategory);
        model.addAttribute("monthlyCashFlow", monthlyCashFlow);
        model.addAttribute("loanCompletion", loanCompletion);
        return "dashboard";
    }
}
