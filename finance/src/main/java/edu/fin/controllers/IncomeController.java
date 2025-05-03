package edu.fin.controllers;

import edu.fin.config.APIConfig;
import edu.fin.models.user.User;
import edu.fin.models.income.IncomeLog;
import edu.fin.models.income.IncomeByPayFrequencyDetail;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
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
public class IncomeController extends AuthenticatedController {
    @Autowired
    private APIConfig ac;
    
    @Autowired
    private RestTemplate rt;

    public IncomeController() {}

    /* Income Logs */
    //##################################################################
    // show income logs
    @GetMapping
    public String showIncomeLogs(HttpSession session, Model model) {        
        Long userId = requireUserId(session);

        // get income logs
        String url = ac.getBaseUrl() + "/income-logs/user/" + userId;
        ResponseEntity<IncomeLog[]> response = rt.getForEntity(url, IncomeLog[].class);
        List<IncomeLog> logs = response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();

        model.addAttribute("incomeLogs", logs);
        model.addAttribute("incomeLog", new IncomeLog());
        return "income";
    }


    @GetMapping("/create")
    public String showIncomeLogCreateForm(Model model, HttpSession session) {
        require(session);

        IncomeLog incomeLog = new IncomeLog();
        model.addAttribute("incomeLog", incomeLog);
        return "components/income/income-log-create";
    }

    // create income log form submission
    @PostMapping("/create")
    public String createIncomeLog(@ModelAttribute IncomeLog log, HttpSession session) {
        User user = requireUser(session);

        String url = ac.getBaseUrl() + "/income-logs";

        log.setUser(user);
        HttpEntity<IncomeLog> request = new HttpEntity<>(log);
        rt.postForObject(url, request, IncomeLog.class);
        return "redirect:/income";
    }

    // delete income log
    @PostMapping("/delete/{id}")
    public String deleteIncomeLog(@PathVariable Long id, HttpSession session) {
        require(session);

        String url = ac.getBaseUrl() + "/income-logs/" + id;
        rt.exchange(url, HttpMethod.DELETE, null, Void.class);
        return "redirect:/income";
    }
    //##################################################################

    /* Income Log Details */
    //##################################################################
    
    // show income log details
    @GetMapping("/{id}")
    public String showIncomeLogDetails(@PathVariable Long id, HttpSession session, Model model) {
        require(session);

        try {
            String url = ac.getBaseUrl() + "/income-logs/" + id + "/details";
            ResponseEntity<List<IncomeByPayFrequencyDetail>> response = rt.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<IncomeByPayFrequencyDetail>>() {});
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
