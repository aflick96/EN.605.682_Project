package edu.fin.models.investment;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="investment_contributions")
public class InvestmentContribution {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long contributionId;
	
	@ManyToOne
	@JoinColumn(name="investment_log_id", nullable=false)
	private InvestmentLog investmentLog;
	
	private LocalDate contributionDate;
	private Double contributionAmount;
	public Long getContributionId() {
		return contributionId;
	}
	public void setContributionId(Long contributionId) {
		this.contributionId = contributionId;
	}
	public InvestmentLog getInvestmentLog() {
		return investmentLog;
	}
	public void setInvestmentLog(InvestmentLog investmentLog) {
		this.investmentLog = investmentLog;
	}
	public LocalDate getContributionDate() {
		return contributionDate;
	}
	public void setContributionDate(LocalDate contributionDate) {
		this.contributionDate = contributionDate;
	}
	public Double getContributionAmount() {
		return contributionAmount;
	}
	public void setContributionAmount(Double contributionAmount) {
		this.contributionAmount = contributionAmount;
	}
}
