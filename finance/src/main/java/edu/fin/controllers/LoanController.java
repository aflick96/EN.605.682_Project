package edu.fin.controllers;

import edu.fin.config.APIConfig;
import edu.fin.utils.auth.Session;
import edu.fin.models.loan.LoanItem;
import edu.fin.models.loan.LoanPayment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/loans")
public class LoanController {
    private APIConfig ac;
    private RestTemplate rt;

    @Autowired
    public LoanController(APIConfig ac, RestTemplate rt) {
        this.ac = ac;
        this.rt = rt;
    }

    @GetMapping
    public String showLoanForm(HttpSession session, Model model) {
        Long userId = Session.checkSessionGetUserId(session);
        if (userId == null) return Session.redirectAuthLogin();

        String url = ac.getBaseUrl() + "/loans/user/" + userId;
        ResponseEntity<LoanItem[]> response = rt.getForEntity(url, LoanItem[].class);
        List<LoanItem> userLoans = Arrays.asList(response.getBody());
        
        System.out.println("User Loans: " + userLoans);
        
        model.addAttribute("loanItem", new LoanItem());
        model.addAttribute("userLoans", userLoans);
        return "loan";
    }

    @PostMapping("/add-item")
    public String addLoanItem(@ModelAttribute("loanItem") LoanItem loanItem, HttpSession session) {
        Long userId = Session.checkSessionGetUserId(session);
        if (userId == null) return Session.redirectAuthLogin();

        String url = ac.getBaseUrl() + "/loans/user/" + userId;
        HttpEntity<LoanItem> request = new HttpEntity<>(loanItem);
        ResponseEntity<LoanItem> response = rt.postForEntity(url, request, LoanItem.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/loans?success=true";
        } else {
            return "redirect:/loans?error=true";
        }
    }

    @GetMapping("/create-payment")
    public String showLoanPaymentForm(@RequestParam(value = "loanItemId", required = false) Long loanItemId, Model model, HttpSession session) {
        Long userId = Session.checkSessionGetUserId(session);
        if (userId == null) return Session.redirectAuthLogin();

        LoanPayment loanPayment = new LoanPayment();
        loanPayment.setLoanItemId(loanItemId);
        model.addAttribute("loanPayment", loanPayment);
        return "components/loan/loan-payment-create";
    }
    
    @PostMapping("/add-payment")
    public String addLoanPayment(@ModelAttribute LoanPayment loanPayment, HttpSession session) {
        Long userId = Session.checkSessionGetUserId(session);
        if (userId == null) return Session.redirectAuthLogin();

        String url = ac.getBaseUrl() + "/loans/payments";
        HttpEntity<LoanPayment> request = new HttpEntity<>(loanPayment);
        rt.postForObject(url, request, String.class);
        return "redirect:/loans";
    }
}
