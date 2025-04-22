package edu.fin.services;

import edu.fin.entities.user.User;
import edu.fin.repositories.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository user_repo;

    public UserService() {}

    public User getUserById(Long userId) {
        Optional<User> user = user_repo.findById(userId);
        return user.orElse(null);
    }

    // Update user filing status
    public void updateUserTaxFilingStatus(Long id, String status) {
        if (id == null || status == null) throw new IllegalArgumentException("ID and status cannot be null");
        if (status != "single" && status != "married") throw new IllegalArgumentException("Invalid status: " + status);

        // Get user and update filing status
        Optional<User> userOpt = user_repo.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setFilingStatus(status);
            user_repo.save(user);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
    }

    public void updateUserPassword(Long id, String password) {
        if (id == null || password == null) throw new IllegalArgumentException("ID and password cannot be null");

        // Get user and update password
        Optional<User> userOpt = user_repo.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(password);
            user_repo.save(user);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
    }

    public void updateUserStateOfResidence(Long id, String state) {
        if (id == null || state == null) throw new IllegalArgumentException("ID and state cannot be null");

        // Get user and update state of residence
        Optional<User> userOpt = user_repo.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setState(state);
            user_repo.save(user);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
    }
}
