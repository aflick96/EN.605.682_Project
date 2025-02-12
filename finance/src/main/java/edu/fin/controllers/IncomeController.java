package edu.fin.controllers;

import edu.fin.config.APIConfig;
import edu.fin.models.user.User;
import edu.fin.models.income.IncomeLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/income")
public class IncomeController {
    
    private APIConfig ac;
    private RestTemplate rt;

    public IncomeController(APIConfig ac, RestTemplate rt) {
        this.ac = ac;
        this.rt = rt;
    }

    // show income logs
    @GetMapping
    public String showIncomeLogs(HttpSession session, Model model) {
        
        // check if user has a session --> redirect to login if not
        User user = checkSession(session);
        if (user == null) { return redirectAuthLogin(); }

        // get income logs
        String url = ac.getIncomeLogsUrl(user.getId());        
        ResponseEntity<IncomeLog[]> response = rt.getForEntity(url, IncomeLog[].class);
        List<IncomeLog> logs = response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();

        model.addAttribute("incomeLogs", logs);
        model.addAttribute("incomeLog", new IncomeLog());
        return "income";
    }

    // create income log form submission
    @PostMapping("/create")
    public String createIncomeLog(@ModelAttribute IncomeLog log, HttpSession session) {
        User user = checkSession(session);
        if (user == null) { return redirectAuthLogin(); }
        
        log.setUser(user);
        HttpEntity<IncomeLog> request = new HttpEntity<>(log);
        rt.postForObject(ac.createIncomeLogUrl(), request, IncomeLog.class);
        return "redirect:/income";
    }

    // delete income log
    @PostMapping("/delete/{id}")
    public String deleteIncomeLog(@PathVariable Long id, HttpSession session) {
        User user = checkSession(session);
        if (user == null) { return redirectAuthLogin(); }

        rt.exchange(ac.deleteIncomeLogUrl(id), HttpMethod.DELETE, null, Void.class);
        return "redirect:/income";
    }

    // session attribute check
    private User checkSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null ? user : null;
    }

    private String redirectAuthLogin() {
        return "redirect:/auth/login";
    }
}
