package edu.fin.controllers;

import edu.fin.models.user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends AuthenticatedController {
	
	@GetMapping("/")
	public String index(HttpSession session) {
		User user = requireUser(session);
		if (user != null) { return "dashboard"; } else { return "login"; }
	}
}
