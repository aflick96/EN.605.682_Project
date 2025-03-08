package edu.fin.services;

import edu.fin.models.user.User;
import edu.fin.repositories.user.UserRepository;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository user_repo;

    public UserService(UserRepository user_repo) {
        this.user_repo = user_repo;
    }

    public User getUserById(Long userId) {
        Optional<User> user = user_repo.findById(userId);
        return user.orElse(null);
    }
}
