package edu.fin.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import edu.fin.entities.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	@NonNull Optional<User> findById(@NonNull Long id);
	Optional<User> findByEmail(String email);
}
