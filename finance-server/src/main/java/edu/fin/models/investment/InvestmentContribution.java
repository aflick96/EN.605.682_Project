package edu.fin.models.investment;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="investment_contributions")
public class InvestmentContribution {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="investment_log_id", nullable=false)
	@JsonBackReference
	private InvestmentLog investmentLog;
	
	private LocalDate contributionDate;
	private Double contributionAmount;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public InvestmentLog getInvestmentLog() { return investmentLog; }
	public void setInvestmentLog(InvestmentLog investmentLog) { this.investmentLog = investmentLog; }
	
	public LocalDate getContributionDate() { return contributionDate;}
	public void setContributionDate(LocalDate contributionDate) { this.contributionDate = contributionDate; }

	public Double getContributionAmount() { return contributionAmount; }
	public void setContributionAmount(Double contributionAmount) { this.contributionAmount = contributionAmount; }

	@Override
	public String toString() {
		return "InvestmentContribution{" +
				"id=" + id +
				", contributionDate=" + contributionDate +
				", contributionAmount=" + contributionAmount +
				'}';
	}
}
