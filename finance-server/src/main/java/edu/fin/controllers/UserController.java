/**
 * UserController.java
 * 
 * This controller handles user operations such as registration, login, and lookup.
 */
package edu.fin.controllers;

import edu.fin.repositories.user.UserRepository;
import edu.fin.utils.user.UserUtil;
import edu.fin.entities.user.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final UserRepository repo;
	private final UserUtil util;
	
	public UserController(UserRepository repo, UserUtil util) {
		this.repo = repo;
		this.util = util;
	}
	
	/**
	 * Retrieves a user by their ID.
	 * 
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		Optional<User> user = repo.findById(id);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Registers a new user if the state is valid and the email is unique.
	 * 
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		if (!util.isValidState(user.getState())) {
			return ResponseEntity.badRequest().body("Invalid state provided");
		}
	
		if (repo.findByEmail(user.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("Duplicate email provided");
		}
		
		repo.save(user);
		return ResponseEntity.ok("User registered successfully");
	}

	/**
	 * Authenticates a user based on email and password.
	 * 
	 */
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		Optional<User> user_ = repo.findByEmail(user.getEmail());
		
		if (user_.isPresent() && user_.get().getPassword().equals(user.getPassword())) {
			return ResponseEntity.ok(user_.get());
		} else {
			return ResponseEntity.status(401).body("Invalid email and password combination");
		}
	}
}
