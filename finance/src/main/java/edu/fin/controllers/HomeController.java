package edu.fin.controllers;

import edu.fin.models.user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String index(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) { return "dashboard"; } else { return "login"; }
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
}
