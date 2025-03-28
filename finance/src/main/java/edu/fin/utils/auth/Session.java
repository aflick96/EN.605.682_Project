package edu.fin.utils.auth;

import edu.fin.models.user.User;
import jakarta.servlet.http.HttpSession;

public class Session {
    
    public Session () {}

    public static User checkSessionGetUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (isUserNull(user)) { return null; }
        return (User) session.getAttribute("user");
    }

    public static Long checkSessionGetUserId(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (isUserNull(user)) { return null;}
        return user.getId();
    }

    public static boolean checkSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return isUserNull(user);
    }
    
    public static String redirectAuthLogin() {
        return "redirect:/auth/login";
    }

    //Check if there is a user in the session
    private static boolean isUserNull(User user) {
        return user == null;
    }
}
