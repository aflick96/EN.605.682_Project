package edu.fin.controllers;

import edu.fin.config.APIConfig;
import edu.fin.models.user.User;
import edu.fin.models.expense.ExpenseLog;
import edu.fin.models.expense.ExpenseItem;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/expenses")
public class ExpenseController extends AuthenticatedController{
    
    private APIConfig ac;
    private RestTemplate rt;

    public ExpenseController(APIConfig ac, RestTemplate rt) {
        this.ac = ac;
        this.rt = rt;
    }

    /* Expense Logs */
    //##################################################################
    @GetMapping
    public String showExpenseLog(Model model, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/expenses/user/" + userId;
        ExpenseLog expenseLog = rt.getForObject(url, ExpenseLog.class);
        model.addAttribute("expenseLog", expenseLog);
        // System.out.println("Expense Log: " + expenseLog);
        return "expenses";
    }

    @GetMapping("add-item")
    public String showExpenseItemForm(@RequestParam(value="expenseLogId", required = false) Long expenseLogId, Model model, HttpSession session) {
        require(session);

        // Create a new expense item and tie it to the expense log
        ExpenseItem item = new ExpenseItem();
        item.setExpenseLogId(expenseLogId);

        // Set the new item as a model attribute to be used in the form
        model.addAttribute("item", item);
        return "components/expense/expense-item-create";
    }
    
    @PostMapping("/add-item")
    public String addExpenseItem(@ModelAttribute("item") ExpenseItem item, HttpSession session) {
        User user = requireUser(session);

        String url = ac.getBaseUrl() + "/expenses/user/" + user.getId() + "/items";
        HttpEntity<ExpenseItem> request = new HttpEntity<>(item);
        rt.postForObject(url, request, ExpenseItem.class);
        return "redirect:/expenses";
    }

    @GetMapping("/update-item")
    public String showUpdateExpenseItemForm(@ModelAttribute ExpenseItem item, Model model, HttpSession session) {
        require(session);
        model.addAttribute("item", item);
        return "components/expense/expense-item-update";
    }

    @PostMapping("/update-item")
    public String updateExpenseItem(@ModelAttribute("item") ExpenseItem item, HttpSession session) {
        User user = requireUser(session);

        String url = ac.getBaseUrl() + "/expenses/user/" + user.getId() + "/items";
        HttpEntity<ExpenseItem> request = new HttpEntity<>(item);
        rt.put(url, request, ExpenseItem.class);
        return "redirect:/expenses";
    }  

    @PostMapping("/delete-item")
    public String deleteExpenseItem(@RequestParam(value="itemId") Long itemId, HttpSession session) {
        User user = requireUser(session);

        String url = ac.getBaseUrl() + "/expenses/user/" + user.getId() + "/items/" + itemId;
        rt.delete(url);
        return "redirect:/expenses";
    }
    //##################################################################
}
