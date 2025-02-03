package edu.fin.models.property;

import edu.fin.models.user.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="loan_items")
public class LoanItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	private String name;
	private Double itemValue;
	private Double loanAmount;
	private Double interestRate;
	private LocalDate startDate;
	private Integer loanTermMonths;
	
	@OneToMany(mappedBy="loanItem", cascade=CascadeType.ALL)
	private List<LoanPayment> loanPayments;

	public Long getLoanItemId() {
		return id;
	}

	public void setLoanItemId(Long loanItemId) {
		this.id = loanItemId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getItemValue() {
		return itemValue;
	}

	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoadAmount(Double loadAmount) {
		this.loanAmount = loadAmount;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Integer getLoanTermMonths() {
		return loanTermMonths;
	}

	public void setLoanTermMonths(Integer loanTermMonths) {
		this.loanTermMonths = loanTermMonths;
	}

	public List<LoanPayment> getLoanPayments() {
		return loanPayments;
	}

	public void setLoanPayments(List<LoanPayment> loanPayments) {
		this.loanPayments = loanPayments;
	}
}
