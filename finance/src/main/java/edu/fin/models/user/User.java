/*
 * User.java
 * 
 * This class represents a user in the system.
 */

package edu.fin.models.user;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String state;
	private String filingStatus;
	private int dependents;
    
	//constructors
    public User() {}
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
    public User(String firstName, String lastName, String email, String password, String state, String filingStatus, int dependents) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.state = state;
		this.filingStatus = filingStatus;
		this.dependents = dependents;
    }

	// getters and setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getState() { return state; }
	public void setState(String state) { this.state = state; }

	public String getFilingStatus() { return filingStatus; }
	public void setFilingStatus(String filingStatus) { this.filingStatus = filingStatus; }

	public int getDependents() { return dependents; }
	public void setDependents(int dependents) { this.dependents = dependents; }

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", state=" + state + "]" + ", filingStatus=" + filingStatus 
				+ ", dependents=" + dependents + "]";
	}
}
