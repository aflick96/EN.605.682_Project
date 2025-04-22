package edu.fin.controllers;

import edu.fin.repositories.user.UserRepository;
import edu.fin.utils.user.UserUtil;
import edu.fin.entities.user.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		Optional<User> user_ = repo.findByEmail(user.getEmail());
		
		if (user_.isPresent() && user_.get().getPassword().equals(user.getPassword())) {
			return ResponseEntity.ok(user_.get());
		} else {
			return ResponseEntity.status(401).body("Invalid email and password combination");
		}
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(repo.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		Optional<User> user = repo.findById(id);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
