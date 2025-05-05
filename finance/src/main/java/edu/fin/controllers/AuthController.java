/*
 * AuthController.java
 * 
 * This controller handles user authentication, including login, registration, and logout.
 */

package edu.fin.controllers;

import edu.fin.models.user.User;
import edu.fin.config.APIConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private RestTemplate rt;

	@Autowired
	private APIConfig ac;
	
	public AuthController() {}
	
	// Render the login page
	@GetMapping("/login")
	public String showLoginPage(Model model) {
		model.addAttribute("error", null);
		return "login";
	}
	
	// Render the registration page
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("error", null);
		return "register";
	}
	
	/**
     * Handle user login submission.
     * If credentials are valid, save user in session and redirect to dashboard.
     * Otherwise, reload login page with an error message.
     */
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        try {
            User user_ = new User(email, password);
			String url = ac.getBaseUrl() + "/users/login";
            User user = rt.postForObject(url, user_, User.class);
            session.setAttribute("user", user);
            return "redirect:/income";        
        } catch (Exception ex) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
	}
	
	/**
     * Handle user registration submission.
     * On success, redirect to login page. On failure, show error.
     */
	@PostMapping("/register")
	public String register(@ModelAttribute User user, Model model) {
		try {
			String url = ac.getBaseUrl() + "/users/register";
			rt.postForObject(url, user, String.class);
			return "redirect:/auth/login";
		} catch (Exception e) {
			model.addAttribute("error", "Registeration failed, try again later.");
			return "register";
		}
	}

	/**
     * Handle logout by invalidating the session.
     */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/auth/login";
	}
}
