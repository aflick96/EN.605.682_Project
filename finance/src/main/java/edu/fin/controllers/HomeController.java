package edu.fin.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends AuthenticatedController {
	
	@GetMapping("/")
	public String index(HttpSession session) {
		return (session.getAttribute("user") != null) ? "forward:/dashboard" : "forward:/auth/login";
	}
}
