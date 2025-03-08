package edu.fin.controllers;

import edu.fin.config.APIConfig;
import edu.fin.utils.auth.Session;
import edu.fin.models.user.User;
import edu.fin.models.expense.ExpenseLog;
import edu.fin.models.expense.ExpenseItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {
    
    private APIConfig ac;
    private RestTemplate rt;

    public ExpenseController(APIConfig ac, RestTemplate rt) {
        this.ac = ac;
        this.rt = rt;
    }

    /* Expense Logs */
    //##################################################################
    @GetMapping
    public String showExpenseLog(HttpSession session, Model model) {
        // Check if user has a session --> redirect to login if not
        Long userId = Session.checkSessionGetUserId(session);
        if (userId == null) return Session.redirectAuthLogin();

        // Get expense logs
        ResponseEntity<String> response = rt.getForEntity(ac.getExpenseLogsUrl(userId), String.class);
        System.out.println("Response: " + response.getBody());
        // ExpenseLog log = response.getBody();
        


        // model.addAttribute("expenseLog", log);
        return "expenses";
    }

    @PostMapping("/add-item")
    public String addExpenseItem(@ModelAttribute ExpenseItem item, HttpSession session) {
        User user = Session.checkSessionGetUser(session);
        if (user == null) return Session.redirectAuthLogin();

        ResponseEntity<ExpenseLog> response = rt.getForEntity(ac.getExpenseLogsUrl(user.getId()), ExpenseLog.class);
        ExpenseLog log = response.getBody();

        if (log != null) {
            item.setExpenseLog(log);
            HttpEntity<ExpenseItem> request = new HttpEntity<>(item);
            rt.postForObject(ac.createExpenseLogItemUrl(user.getId()), request, ExpenseItem.class);
        }

        return "redirect:/expenses";
    }

    @PostMapping("/delete-item/{expenseItemId}")
    public String deleteExpenseItem(@PathVariable Long expenseItemId, HttpSession session) {
        User user = Session.checkSessionGetUser(session);
        if (user == null) return Session.redirectAuthLogin();
        rt.delete(ac.deleteExpenseLogItemUrl(expenseItemId));
        return "redirect:/expenses";
    }
    //##################################################################
}
