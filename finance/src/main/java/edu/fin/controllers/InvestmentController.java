package edu.fin.controllers;

import edu.fin.config.APIConfig;
import edu.fin.utils.auth.Session;
import edu.fin.models.investment.*;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/investment")
public class InvestmentController {
    @Autowired
    private APIConfig ac;

    @Autowired
    private RestTemplate rt;

    public InvestmentController(APIConfig ac, RestTemplate rt) {
        this.ac = ac;
        this.rt = rt;
    }

    // Called when the Investments page on the main navigation bar is clicked
    // This retrieves any investment logs associated with the user and displays them
    @GetMapping
    public String showInvestments(Model model, HttpSession session) {
        Long userId = Session.checkSessionGetUserId(session);
        if (userId == null) return Session.redirectAuthLogin();

        String url = ac.getBaseUrl() + "/investments/user/" + userId;
        InvestmentLog[] investmentLogs = rt.getForObject(url, InvestmentLog[].class);
        model.addAttribute("investmentLogs", investmentLogs);

        System.out.println("Investment Logs: " + Arrays.asList(investmentLogs));
        return "investments";
    }

    // #################################################################################
    // Called when the Add New Investment Item button is clicked
    // This creates a new investment log item and displays the form to fill out
    @GetMapping("/add-investment-log")
    public String showInvestmentCreateForm(Model model, HttpSession session) {
        Long userId = Session.checkSessionGetUserId(session);
        if (userId == null) return Session.redirectAuthLogin();

        InvestmentLog investmentLog = new InvestmentLog();
        model.addAttribute("investmentLog", investmentLog);
        return "components/investment/investment-create";
    }

    // Called when the form to add a new investment log is submitted
    // This sends the investment log to the API and redirects to the investments page
    @PostMapping("/add-investment-log")
    public String addInvestment(@ModelAttribute("investmentLog") InvestmentLog investmentLog, HttpSession session) {
        Long userId = Session.checkSessionGetUserId(session);
        if (userId == null) return Session.redirectAuthLogin();

        String url = ac.getBaseUrl() + "/investments/user/" + userId;
        HttpEntity<InvestmentLog> request = new HttpEntity<>(investmentLog);
        ResponseEntity<InvestmentLog> response = rt.postForEntity(url, request, InvestmentLog.class);
        
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/investment?success=true";
        } else {
            return "redirect:/investment?error=true";
        }
    }
    // #################################################################################

    // ##################################################################################
    // Called when the Add New Investment Contribution button is clicked
    // This creates a new investment contribution item and displays the form to fill out
    @GetMapping("/add-investment-contribution")
    public String showInvestmentContributionCreateForm(@RequestParam(value="investmentLogId", required=false) Long investmentLogId, Model model, HttpSession session) {
        Long userId = Session.checkSessionGetUserId(session);
        if (userId == null) return Session.redirectAuthLogin();

        // Create a new investment contribution object and add the investment log id of the one triggered
        InvestmentContribution investmentContribution = new InvestmentContribution();
        investmentContribution.setInvestmentLogId(investmentLogId);
        model.addAttribute("investmentContribution", investmentContribution);
        return "components/investment/investment-contribution-create";
    }

    // Called when the form to add a new investment contribution is submitted
    // This sends the investment contribution to the API and redirects to the investments page
    @PostMapping("/add-investment-contribution")
    public String addInvestmentContribution(@ModelAttribute InvestmentContribution investmentContribution, HttpSession session) {
        Long userId = Session.checkSessionGetUserId(session);
        if (userId == null) return Session.redirectAuthLogin();

        System.out.println("Investment Contribution: " + investmentContribution);

        // String url = ac.getBaseUrl() + "/investments/contributions";
        // HttpEntity<InvestmentContribution> request = new HttpEntity<>(investmentContribution);
        // rt.postForObject(url, request, String.class);
        return "redirect:/investment";
    }
    // ##################################################################################

}
