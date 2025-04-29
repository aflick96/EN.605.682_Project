package edu.fin.controllers;

import edu.fin.config.APIConfig;
import edu.fin.models.investment.*;
import edu.fin.services.InvestmentService;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.List;
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
public class InvestmentController extends AuthenticatedController {
    @Autowired
    private APIConfig ac;

    @Autowired
    private RestTemplate rt;

    @Autowired
    private InvestmentService investmentService;

    public InvestmentController() {}

    // Called when the Investments page on the main navigation bar is clicked
    // This retrieves any investment logs associated with the user and displays them
    @GetMapping
    public String showInvestments(Model model, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/investments/user/" + userId;
        InvestmentLog[] investmentLogs = rt.getForObject(url, InvestmentLog[].class);
        model.addAttribute("investmentLogs", investmentLogs);
        return "investments";
    }

    // #################################################################################
    // Called when the Add New Investment Item button is clicked
    // This creates a new investment log item and displays the form to fill out
    @GetMapping("/add-investment-log")
    public String showInvestmentCreateForm(Model model, HttpSession session) {
        require(session);

        InvestmentLog investmentLog = new InvestmentLog();
        model.addAttribute("investmentLog", investmentLog);
        return "components/investment/investment-create";
    }

    // Called when the form to add a new investment log is submitted
    // This sends the investment log to the API and redirects to the investments page
    @PostMapping("/add-investment-log")
    public String addInvestment(@ModelAttribute("investmentLog") InvestmentLog investmentLog, HttpSession session) {
        Long userId = requireUserId(session);

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
        require(session);

        // Create a new investment contribution object and add the investment log id of the one triggered
        InvestmentContribution investmentContribution = new InvestmentContribution();
        InvestmentContributions investmentContributions = new InvestmentContributions();
        investmentContribution.setInvestmentLogId(investmentLogId);
        model.addAttribute("investmentContribution", investmentContribution);
        model.addAttribute("investmentContributions", investmentContributions);
        return "components/investment/investment-contribution-create";
    }

    // Called when the form to add a new investment contribution is submitted
    // This sends the investment contribution to the API and redirects to the investments page
    @PostMapping("/add-investment-contribution")
    public String addInvestmentContribution(@RequestParam(value="investmentLogId", required=false) Long investmentLogId, @ModelAttribute InvestmentContribution investmentContribution, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/investments/user/" + userId + "/log/" + investmentLogId + "/contribution";
        HttpEntity<InvestmentContribution> request = new HttpEntity<>(investmentContribution);
        rt.postForObject(url, request, String.class);
        return "redirect:/investment";
    }
    // ##################################################################################

    @PostMapping("/add-investment-contributions")
    public String addInvestmentContributions(@RequestParam(value="investmentLogId", required=false) Long investmentLogId, @ModelAttribute InvestmentContributions investmentContributions, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/investments/user/" + userId + "/log/" + investmentLogId + "/contributions";
        HttpEntity<InvestmentContributions> request = new HttpEntity<>(investmentContributions);
        rt.postForObject(url, request, InvestmentContributions.class);
        return "redirect:/investment";
    }

    @GetMapping("/edit-investment-contributions")
    public String showInvestmentContributionUpdateForm(@RequestParam(value="investmentLogId", required=false) Long investmentLogId, Model model, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/investments/user/" + userId + "/log/" + investmentLogId + "/contribution";
        InvestmentContribution[] investmentContributions = rt.getForObject(url, InvestmentContribution[].class);
        InvestmentContributionsWrapper wrapper = new InvestmentContributionsWrapper();
        wrapper.setInvestmentContributions(Arrays.asList(investmentContributions));
        model.addAttribute("investmentContributionsWrapper", wrapper);
        return "components/investment/investment-contribution-update";
    }

    @PostMapping("/edit-investment-contributions")
    public String updateInvestmentContributions(@RequestParam(value="investmentLogId", required=false) Long investmentLogId, @ModelAttribute("investmentContributionsWrapper") InvestmentContributionsWrapper wrapper, HttpSession session) {
        Long userId = requireUserId(session);
        String url = ac.getBaseUrl() + "/investments/user/" + userId + "/log/" + investmentLogId + "/contribution";
        HttpEntity<List<InvestmentContribution>> request = new HttpEntity<>(wrapper.getInvestmentContributions());
        rt.put(url, request, List.class);
        return "redirect:/investment";
    }

    // Called when the delete button on an investment log is clicked
    @PostMapping("/delete-investment-log")
    public String deleteInvestmentLog(@RequestParam(value="investmentLogId") Long investmentLogId, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/investments/user/" + userId + "/log/" + investmentLogId;
        rt.delete(url);
        return "redirect:/investment";
    }

    // What if investment table endpoint
    @GetMapping("/what-if-investment-table")
    public String showWhatIfInvestmentTable(
        @RequestParam(value="investmentLogId", required=false) Long investmentLogId, 
        @RequestParam(required=false, defaultValue="0") double weeklyContribution, 
        @RequestParam(required=false, defaultValue="0") double annualReturn,
        Model model, HttpSession session) {
        require(session);

        // Get the investment log and contributions applied to it
        Long userId = requireUserId(session);
        String investmentUrl = ac.getBaseUrl() + "/investments/user/" + userId + "/log/" + investmentLogId;
        String contributionUrl = ac.getBaseUrl() + "/investments/user/" + userId + "/log/" + investmentLogId + "/contribution";
        InvestmentLog investmentLog = rt.getForObject(investmentUrl, InvestmentLog.class);
        InvestmentContribution[] investmentContributions = rt.getForObject(contributionUrl, InvestmentContribution[].class);

        if (investmentLog != null) {
            InvestmentScenarioResult result = investmentService.computeInvestmentScenarioTable(investmentLog, investmentContributions, weeklyContribution, annualReturn);
            model.addAttribute("investment", investmentLog);
            model.addAttribute("weeklyContribution", weeklyContribution);
            model.addAttribute("annualReturn", annualReturn);
            model.addAttribute("tableRows", result.getTableRows());
        }

        return "components/investment/what-if-investment-table";
    }
}
