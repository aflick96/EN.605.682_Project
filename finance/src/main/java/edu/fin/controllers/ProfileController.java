package edu.fin.controllers;

import edu.fin.config.APIConfig;
import edu.fin.models.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.HttpSession;

/**
 * Handles GET requests to /profile route.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController extends AuthenticatedController {
    @Autowired
    private APIConfig ac;

    @Autowired
    private RestTemplate rt;

    public ProfileController() {}

    /**
     * Retrieves the currently logged-in user's profile information and passes it to the view.
     */
    @GetMapping
    public String showProfile(Model model, HttpSession session) {
        Long userId = requireUserId(session);

        String url = ac.getBaseUrl() + "/users/" + userId;
        User user = rt.getForObject(url, User.class);
        model.addAttribute("user", user);

        return "profile";
    }
}
