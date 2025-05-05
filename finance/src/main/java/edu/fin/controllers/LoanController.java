/*
 * LoanController.java
 * 
 * This controller manages loan-related operations including creating, editing,
 */

package edu.fin.controllers;

import edu.fin.config.APIConfig;
import edu.fin.models.loan.LoanItem;
import edu.fin.models.loan.LoanPayment;
import edu.fin.models.loan.LoanPayments;
import edu.fin.models.loan.LoanPaymentsWrapper;
import edu.fin.models.loan.LoanScenarioResult;
import edu.fin.services.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Controller to manage loan-related operations including
 * creating, editing, deleting, and what-if loan payments.
 */
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

    /**
     * Displays all loans for the logged-in user.
     */
    @GetMapping
    public String showLoans(Model model, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/loans/user/" + userId;
        LoanItem[] loanItems = rt.getForObject(url, LoanItem[].class);
        model.addAttribute("loanItems", loanItems);
        return "loans";
    }

    /**
     * Shows the form to add a new loan item by popping out the loan-create modal.
     */
    @GetMapping("/add-item")
    public String showLoanItemCreateForm(Model model, HttpSession session) {
        require(session);

        LoanItem loanItem = new LoanItem();
        model.addAttribute("loanItem", loanItem);
        return "components/loan/loan-create";
    }

    /**
     * Submits the new loan item to the backend API.
     */
    @PostMapping("/add-item")
    public String addLoanItem(@ModelAttribute("loanItem") LoanItem loanItem, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/loans/user/" + userId;
        HttpEntity<LoanItem> request = new HttpEntity<>(loanItem);
        rt.postForEntity(url, request, LoanItem.class);
        return "redirect:/loans";
    }

    /**
     * Shows the form to add a single loan payment by popping out the loan-payment-create modal.
     */
    @GetMapping("/add-payment")
    public String showLoanPaymentForm(@RequestParam(value = "loanItemId", required = false) Long loanItemId, Model model, HttpSession session) {
        Long userId = requireUserId(session);

        String loanItemUrl = ac.getBaseUrl() + "/loans/user/" + userId + "/item/" + loanItemId;
        LoanItem loanItem = rt.getForObject(loanItemUrl, LoanItem.class);
        if (loanItem == null) return "redirect:/loans"; 
        
        LocalDate loanStartDate = loanItem.getStartDate();
        LocalDate loanEndDate = loanStartDate.plusMonths(loanItem.getLoanTermMonths());
        LoanPayment loanPayment = new LoanPayment();
        LoanPayments loanPayments = new LoanPayments();
        loanPayment.setLoanItemId(loanItemId);
        loanPayments.setLoanItemId(loanItemId);
        model.addAttribute("loanPayment", loanPayment);
        model.addAttribute("loanPayments", loanPayments);
        model.addAttribute("loanStartDate", loanStartDate);
        model.addAttribute("loanEndDate", loanEndDate);

        return "components/loan/loan-payment-create";
    }

    /**
     * Submits a single loan payment to the backend.
     */
    @PostMapping("/add-payment")
    public String addLoanPayment(@RequestParam(value="loanItemId", required=false) Long loanItemId, @ModelAttribute LoanPayment loanPayment, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/loans/user/" + userId + "/item/" + loanItemId + "/payment";
        HttpEntity<LoanPayment> request = new HttpEntity<>(loanPayment);
        rt.postForObject(url, request, String.class);
        return "redirect:/loans";
    }

    /**
     * Submits multiple loan payments to the backend.
     */
    @PostMapping("/add-payments")
    public String addLoanPayments(@RequestParam(value="loanItemId", required=false) Long loanItemId, @ModelAttribute LoanPayments loanPayments, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/loans/user/" + userId + "/item/" + loanItemId + "/payments";
        HttpEntity<LoanPayments> request = new HttpEntity<>(loanPayments);
        rt.postForObject(url, request, LoanPayments.class);
        return "redirect:/loans";
    }

    // load the loan payment update form with the loan payment data
    @GetMapping("/edit-loan-payments")
    public String showLoanPaymentUpdateForm(@RequestParam(value="loanItemId", required=false) Long loanItemId, Model model, HttpSession session, HttpServletResponse response) throws IOException {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/loans/user/" + userId + "/item/" + loanItemId + "/payment";
        LoanPayment[] loanPayments = rt.getForObject(url, LoanPayment[].class);        
        
        // Display a message if no loan payments are found
        if (loanPayments == null || loanPayments.length == 0) {
            response.setHeader("X-Client-Message", "No loan payments found for this loan item.");
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return null;
        }

        LoanPaymentsWrapper wrapper = new LoanPaymentsWrapper();
        wrapper.setLoanPayments(Arrays.asList(loanPayments));
        model.addAttribute("loanPaymentsWrapper", wrapper);
        return "components/loan/loan-payment-update";
    }

    // update the loan payment data in the database
    @PostMapping("/edit-loan-payments")
    public String updateLoanPayments(@RequestParam(value="loanItemId", required=false) Long loanItemId, @ModelAttribute("loanPaymentsWrapper") LoanPaymentsWrapper wrapper, HttpSession session) {
        Long userId = requireUserId(session);
        String url = ac.getBaseUrl() + "/loans/user/" + userId + "/item/" + loanItemId + "/payment";
        HttpEntity<List<LoanPayment>> request = new HttpEntity<>(wrapper.getLoanPayments());
        rt.put(url, request, List.class);
        return "redirect:/loans";
    }

    /**
     * Deletes a loan item based on its ID.
     */
    @PostMapping("/delete-loan-item")
    public String deleteLoanItem(@RequestParam(value = "loanItemId", required = false) Long loanItemId, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/loans/user/" + userId + "/item/" + loanItemId;
        rt.delete(url);
        return "redirect:/loans";
    }

    /**
     * Shows a "What-if" loan repayment scenario table based on modified terms.
     */
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
            model.addAttribute("loanTerm", result.getLoanTerms());
            model.addAttribute("tableRows", result.getTableRows());
        }

        return "components/loan/what-if-loan-table";
    }
}
