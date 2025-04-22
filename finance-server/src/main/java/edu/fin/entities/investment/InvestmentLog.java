package edu.fin.entities.investment;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import edu.fin.entities.user.User;

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
	@JsonManagedReference
	private List<InvestmentContribution> contributions;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public LocalDate getStartDate() { return startDate; }
	public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

	public LocalDate getEndDate() { return endDate; }
	public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

	public Double getExpectedAnnualReturn() { return expectedAnnualReturn; }
	public void setExpectedAnnualReturn(Double expectedAnnualReturn) { this.expectedAnnualReturn = expectedAnnualReturn; }

	public List<InvestmentContribution> getContributions() { return contributions; }
	public void setContributions(List<InvestmentContribution> contributions) { this.contributions = contributions; }

	@Override
	public String toString() {
		return "InvestmentLog{" +
				"id=" + id +
				", name='" + name + '\'' +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", expectedAnnualReturn=" + expectedAnnualReturn +
				'}';
	}
}
