package edu.fin.controllers;

import edu.fin.models.user.User;
import edu.fin.config.APIConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpEntity;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AuthenticatedController {
    @Autowired
    private APIConfig ac;

    @Autowired
    private RestTemplate rt;

    public DashboardController() {}

    @GetMapping
    public String showDashboard(Model model, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/users/" + userId;
        User user = rt.getForObject(url, User.class);
        model.addAttribute("user", user);

        return "dashboard";
    }
}
