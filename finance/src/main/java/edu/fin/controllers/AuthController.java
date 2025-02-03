package edu.fin.controllers;

import edu.fin.models.user.User;
import edu.fin.config.APIConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	private APIConfig ac;
	
	public AuthController(APIConfig ac) {
		this.ac = ac;
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegisterPage() {
		return "register";
	}
	
	@GetMapping("/dashboard")
	public String showDashboardPage() {
		return "dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/auth/login";
	}
	
	@PostMapping("/login")
	public String login(
			@RequestParam String email,
			@RequestParam String password,
			Model model,
			HttpSession session
			) {
		RestTemplate rt = new RestTemplate();
		try {
			User user_ = new User();
			user_.setEmail(email);
			user_.setPassword(password);
			User user = rt.postForObject(ac.userLoginUrl(), user_, User.class);
			session.setAttribute("user", user);
			return "redirect:/auth/dashboard";
		} catch (Exception e) {
			model.addAttribute("error", "Invalid credentials");
			return "login";
		}
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user, Model model) {
		RestTemplate rt = new RestTemplate();
		try {
			rt.postForObject(ac.userRegisterUrl(), user, String.class);
			return "redirect:/auth/login";
		} catch (Exception e) {
			model.addAttribute("error", "Register failed, try again later.");
			return "signup";
		}
	}
}
