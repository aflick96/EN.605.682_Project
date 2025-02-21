package edu.fin.models.investment;

import edu.fin.models.user.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="investment_logs")
public class InvestmentLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private Double expectedAnnualReturn;
	
	@OneToMany(mappedBy="investmentLog", cascade=CascadeType.ALL)
	private List<InvestmentContribution> contributions;

	public Long getInvestmentLogId() {
		return id;
	}

	public void setInvestmentLogId(Long investmentLogId) {
		this.id = investmentLogId;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Double getExpectedAnnualReturn() {
		return expectedAnnualReturn;
	}

	public void setExpectedAnnualReturn(Double expectedAnnualReturn) {
		this.expectedAnnualReturn = expectedAnnualReturn;
	}

	public List<InvestmentContribution> getContributions() {
		return contributions;
	}

	public void setContributions(List<InvestmentContribution> contributions) {
		this.contributions = contributions;
	}
}
