package edu.fin.entities.loan;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import edu.fin.entities.user.User;

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
	@JsonManagedReference
	private List<LoanPayment> loanPayments;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public Double getItemValue() { return itemValue;}
	public void setItemValue(Double itemValue) { this.itemValue = itemValue; }

	public Double getLoanAmount() { return loanAmount; }
	public void setLoanAmount(Double loanAmount) { this.loanAmount = loanAmount;}

	public Double getInterestRate() { return interestRate; }
	public void setInterestRate(Double interestRate) { this.interestRate = interestRate; }

	public LocalDate getStartDate() { return startDate; }
	public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

	public Integer getLoanTermMonths() { return loanTermMonths; }
	public void setLoanTermMonths(Integer loanTermMonths) { this.loanTermMonths = loanTermMonths;}

	public List<LoanPayment> getLoanPayments() { return loanPayments;}
	public void setLoanPayments(List<LoanPayment> loanPayments) { this.loanPayments = loanPayments; }

	@Override
	public String toString() {
		return "LoanItem{" +
				"id=" + id +
				", name='" + name + '\'' +
				", itemValue=" + itemValue +
				", loanAmount=" + loanAmount +
				", interestRate=" + interestRate +
				", startDate=" + startDate +
				", loanTermMonths=" + loanTermMonths +
				'}';
	}
}
