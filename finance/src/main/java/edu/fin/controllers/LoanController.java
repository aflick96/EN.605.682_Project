package edu.fin.controllers;

import edu.fin.config.APIConfig;
import edu.fin.models.loan.LoanItem;
import edu.fin.models.loan.LoanPayment;
import edu.fin.models.loan.LoanScenarioResult;
import edu.fin.services.LoanService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/loans")
public class LoanController extends AuthenticatedController {
    @Autowired
    private APIConfig ac;
    
    @Autowired
    private RestTemplate rt;

    @Autowired
    private LoanService loanService;

    public LoanController() {}

    @GetMapping
    public String showLoans(Model model, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/loans/user/" + userId;
        LoanItem[] loanItems = rt.getForObject(url, LoanItem[].class);
        model.addAttribute("loanItems", loanItems);
        return "loans";
    }

    @GetMapping("/add-item")
    public String showLoanItemCreateForm(Model model, HttpSession session) {
        require(session);

        LoanItem loanItem = new LoanItem();
        model.addAttribute("loanItem", loanItem);
        return "components/loan/loan-create";
    }

    @PostMapping("/add-item")
    public String addLoanItem(@ModelAttribute("loanItem") LoanItem loanItem, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/loans/user/" + userId;
        HttpEntity<LoanItem> request = new HttpEntity<>(loanItem);
        rt.postForEntity(url, request, LoanItem.class);
        return "redirect:/loans";
    }

    @GetMapping("/add-payment")
    public String showLoanPaymentForm(@RequestParam(value = "loanItemId", required = false) Long loanItemId, Model model, HttpSession session) {
        require(session);

        LoanPayment loanPayment = new LoanPayment();
        loanPayment.setLoanItemId(loanItemId);
        model.addAttribute("loanPayment", loanPayment);
        return "components/loan/loan-payment-create";
    }
    
    @PostMapping("/add-payment")
    public String addLoanPayment(@RequestParam(value="loanItemId", required=false) Long loanItemId, @ModelAttribute LoanPayment loanPayment, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/loans/user/" + userId + "/item/" + loanItemId + "/payment";
        HttpEntity<LoanPayment> request = new HttpEntity<>(loanPayment);
        rt.postForObject(url, request, String.class);
        return "redirect:/loans";
    }

    // load the loan payment update form with the loan payment data
    @GetMapping("/edit-loan-payments")
    public String showLoanPaymentUpdateForm(@RequestParam(value="loanItemId", required=false) Long loanItemId, Model model, HttpSession session) {
        Long userId = requireUserId(session);
        String url = ac.getBaseUrl() + "/loans/user/" + userId + "/item/" + loanItemId + "/payment";
        LoanPayment[] loanPayments = rt.getForObject(url, LoanPayment[].class);
        model.addAttribute("loanPayments", loanPayments);
        return "components/loan/loan-payment-update";
    }

    // update the loan payment data in the database
    @PostMapping("edit-loan-payments")
    public String updateLoanPayments(@RequestParam(value="loanItemId", required=false) Long loanItemId, @ModelAttribute LoanPayment[] loanPayments, HttpSession session) {
        Long userId = requireUserId(session);
        String url = ac.getBaseUrl() + "/loans/user/" + userId + "/item/" + loanItemId + "/payment";
        HttpEntity<LoanPayment[]> request = new HttpEntity<>(loanPayments);
        rt.put(url, request, LoanPayment[].class);
        return "redirect:/loans";
    }

    @PostMapping("/delete-loan-item")
    public String deleteLoanItem(@RequestParam(value = "loanItemId", required = false) Long loanItemId, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/loans/user/" + userId + "/item/" + loanItemId;
        rt.delete(url);
        return "redirect:/loans";
    }

    @GetMapping("/what-if-loan-table")
    public String showWhatIfLoanTable(
        @RequestParam(value = "loanItemId", required = false) Long loanItemId,
        @RequestParam(value = "monthlyPayment", required = false, defaultValue="0") Double monthlyPayment,
        @RequestParam(value = "interestRate", required = false, defaultValue="0") Double interestRate,
        @RequestParam(value = "loanTerm", required = false, defaultValue="0") Integer loanTerm,
        Model model, HttpSession session) {
        
        Long userId = requireUserId(session);
        String loanItemUrl = ac.getBaseUrl() + "/loans/user/" + userId + "/item/" + loanItemId;
        String loanPaymentUrl = ac.getBaseUrl() + "/loans/user/" + userId + "/item/" + loanItemId + "/payment";
        
        LoanItem loanItem = rt.getForObject(loanItemUrl, LoanItem.class);
        LoanPayment[] loanPayments = rt.getForObject(loanPaymentUrl, LoanPayment[].class);

        if (loanItem != null) {
            LoanScenarioResult result = loanService.computeLoanScenarioTable(loanItem, loanPayments, monthlyPayment, interestRate, loanTerm);    
            model.addAttribute("loan", loanItem);
            model.addAttribute("monthlyPayment", result.getScenarioPayment());
            model.addAttribute("interestRate", interestRate);
            model.addAttribute("loanTerm", loanTerm);
            model.addAttribute("tableRows", result.getTableRows());
        }

        return "components/loan/what-if-loan-table";
    }
}
