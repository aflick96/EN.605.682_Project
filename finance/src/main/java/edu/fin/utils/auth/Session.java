/*
 * Session.java
 * 
 * This class handles user session management in a web application.
 */
package edu.fin.utils.auth;

import edu.fin.models.user.User;
import jakarta.servlet.http.HttpSession;

public class Session {
    
    // constructor
    public Session () {}

    // Check if the user is logged in and return the user object
    public static User checkSessionGetUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (isUserNull(user)) { return null; }
        return (User) session.getAttribute("user");
    }

    // Check if the user is logged in and return the user ID
    public static Long checkSessionGetUserId(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (isUserNull(user)) { return null;}
        return user.getId();
    }

    // Check if the user is logged in and return true if not
    public static boolean checkSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return isUserNull(user);
    }
    
    // Check if the user is logged in and return true if not, and redirect to login page
    public static String redirectAuthLogin() {
        return "redirect:/auth/login";
    }

    //Check if there is a user in the session
    private static boolean isUserNull(User user) {
        return user == null;
    }
}
