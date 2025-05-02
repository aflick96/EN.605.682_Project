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
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegisterPage() {
		return "register";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        try {
            User user_ = new User(email, password);
			String url = ac.getBaseUrl() + "/users/login";
			System.out.println("URL: " + url);
            User user = rt.postForObject(url, user_, User.class);
            session.setAttribute("user", user);
            return "redirect:/dashboard";        
        } catch (Exception ex) {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user, Model model) {
		RestTemplate rt = new RestTemplate();
		try {
			String url = ac.getBaseUrl() + "/users/register";
			rt.postForObject(url, user, String.class);
			return "redirect:/auth/login";
		} catch (Exception e) {
			model.addAttribute("error", "Register failed, try again later.");
			return "signup";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/auth/login";
	}
}
