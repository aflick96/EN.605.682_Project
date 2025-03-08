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
	
	// @GetMapping("/income")
	// public String income() {
	// 	return "income";
	// }

	// @GetMapping("/expenses")
	// public String expenses() {
	// 	return "expenses";
	// }

	@GetMapping("/assets-liabilities")
	public String assetsLiabilities() {
		return "assets-liabilities";
	}

	@GetMapping("/investments")
	public String investments() {
		return "investments";
	}

	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}	
}
