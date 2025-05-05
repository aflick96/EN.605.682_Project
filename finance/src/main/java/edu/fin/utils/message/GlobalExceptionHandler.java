/*
 * GlobalExceptionHandler.java
 * 
 * This class handles global exceptions in the application.
 */
package edu.fin.utils.message;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    // Handle NotAuthenticatedException and redirect to login page
    @ExceptionHandler(NotAuthenticatedException.class)
    public String handleNotAuthenticated(HttpServletRequest request) {
        return "redirect:/auth/login";
    }
}
