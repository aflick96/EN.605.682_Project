package edu.fin.controllers;

import edu.fin.config.APIConfig;
import edu.fin.models.user.User;
import edu.fin.models.income.IncomeLog;
import edu.fin.models.income.IncomeByPayFrequencyDetail;
import edu.fin.utils.auth.Session;
import org.springframework.stereotype.Controller;
import org.springframework.core.ParameterizedTypeReference;
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

    /* Income Logs */
    //##################################################################
    // show income logs
    @GetMapping
    public String showIncomeLogs(HttpSession session, Model model) {        
        // check if user has a session --> redirect to login if not
        Long userId = Session.checkSessionGetUserId(session);
        if (userId == null) return Session.redirectAuthLogin();

        // get income logs      
        ResponseEntity<IncomeLog[]> response = rt.getForEntity(ac.getIncomeLogsUrl(userId), IncomeLog[].class);
        List<IncomeLog> logs = response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();

        model.addAttribute("incomeLogs", logs);
        model.addAttribute("incomeLog", new IncomeLog());
        return "income";
    }

    // create income log form submission
    @PostMapping("/create")
    public String createIncomeLog(@ModelAttribute IncomeLog log, HttpSession session) {
        User user = Session.checkSessionGetUser(session);
        if (user == null) return Session.redirectAuthLogin();

        log.setUser(user);
        HttpEntity<IncomeLog> request = new HttpEntity<>(log);
        rt.postForObject(ac.createIncomeLogUrl(), request, IncomeLog.class);
        return "redirect:/income";
    }

    // delete income log
    @PostMapping("/delete/{id}")
    public String deleteIncomeLog(@PathVariable Long id, HttpSession session) {
        if (Session.checkSession(session)) return Session.redirectAuthLogin(); 

        rt.exchange(ac.deleteIncomeLogUrl(id), HttpMethod.DELETE, null, Void.class);
        return "redirect:/income";
    }
    //##################################################################

    /* Income Log Details */
    //##################################################################
    
    // show income log details
    @GetMapping("/{id}")
    public String showIncomeLogDetails(@PathVariable Long id, HttpSession session, Model model) {
        if (Session.checkSession(session)) return Session.redirectAuthLogin();

        try {
            ResponseEntity<List<IncomeByPayFrequencyDetail>> response = rt.exchange(ac.getIncomeLogDetails(id), HttpMethod.GET, null, new ParameterizedTypeReference<List<IncomeByPayFrequencyDetail>>() {});
            List<IncomeByPayFrequencyDetail> detail = response.getBody();
            if (detail == null) detail = List.of();
    
            model.addAttribute("incomeLogDetails", detail);
            return "components/income/income-logger-detail";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to load income log details");
            return "error";
        }
    }
    //##################################################################
}
