/*
 * HomeController.java
 * 
 * This controller handles the root URL ("/") and redirects the user to the appropriate page based on their login status.
 */
package edu.fin.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles GET requests to the root URL ("/").
 * Redirects the user to the dashboard if logged in, or to the login page if not.
 */
@Controller
public class HomeController extends AuthenticatedController {
	
	// Base route for the application. If authenticated redirect to dashboard otherwise to login page.
	@GetMapping("/")
	public String index(HttpSession session) {
		return (session.getAttribute("user") != null) ? "forward:/dashboard" : "forward:/auth/login";
	}
}
