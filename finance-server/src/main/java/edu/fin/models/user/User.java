package edu.fin.models.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
	//Fields

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true, length=100)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;

	@Column(name="state_of_residence", nullable=false)
	private String state;
	
	@Column(name="tax_filing_status", nullable=false)
	private String filingStatus;

	@Column(name="dependents", nullable=false)
	private int dependents;

	@Column(name="created_at", nullable=false, updatable=false)
	private LocalDateTime createdAt;
	
	//Default Constructor
	public User() {
		this.createdAt = LocalDateTime.now();
	}
	
	//Constructor
	public User(String email, String password, String firstName, String lastName, String state, String filingStatus, int dependents) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.state = state;
		this.filingStatus = filingStatus;
		this.dependents = dependents;
		this.createdAt = LocalDateTime.now();
	}


	//Getters and Setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getState() { return state; }
	public void setState(String state) { this.state = state; }

	public String getFilingStatus() { return filingStatus; }
	public void setFilingStatus(String filingStatus) { this.filingStatus = filingStatus; }

	public int getDependents() { return dependents; }
	public void setDependents(int dependents) { this.dependents = dependents; }

	public LocalDateTime getCreatedAt() { return createdAt; }
	public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", state=" + state + ", filingStatus=" + filingStatus + ", dependents=" + dependents
				 + ", createdAt=" + createdAt + "]";
	}
}
