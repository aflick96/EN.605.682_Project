/*
 * AuthenticatedController.java
 * 
 * This abstract class provides methods to check if a user is authenticated and has a valid session.
 */

package edu.fin.controllers;

import edu.fin.models.user.User;
import edu.fin.utils.auth.Session;
import edu.fin.utils.message.NotAuthenticatedException;
import jakarta.servlet.http.HttpSession;

public abstract class AuthenticatedController {
    
    // Check if the user is authenticated and has a valid session
    protected Long requireUserId(HttpSession session) {
        Long userId = Session.checkSessionGetUserId(session);
        if (userId == null) throw new NotAuthenticatedException();
        return userId;
    }

    // Check if the user is authenticated and has a valid session
    protected User requireUser(HttpSession session) {
        User user = Session.checkSessionGetUser(session);
        if (user == null) throw new NotAuthenticatedException();
        return user;
    }

    // Check if the user is authenticated and has a valid session
    protected void require(HttpSession session) {
        if (Session.checkSessionGetUserId(session) == null) throw new NotAuthenticatedException();
    }

    // Redirect to the login page if the user is not authenticated
    protected String redirectToLogin() {
        return "redirect:/auth/login";
    }
}
