/*
 * InvestmentContributionRequest.java
 * 
 * This class represents a request to add a contribution to an investment log. It contains the ID of the investment log, the date of the contribution, and the amount of the contribution.
 */
package edu.fin.dtos.investment;

import java.time.LocalDate;

public class InvestmentContributionRequest {
    private Long id;
    private Long investmentLogId;
    private LocalDate contributionDate;
    private double contributionAmount;

    // constructors
    public InvestmentContributionRequest() {}
    public InvestmentContributionRequest(Long id, Long investmentLogId, LocalDate contributionDate, double contributionAmount) {
        this.id = id;
        this.investmentLogId = investmentLogId;
        this.contributionDate = contributionDate;
        this.contributionAmount = contributionAmount;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInvestmentLogId() { return investmentLogId; }
    public void setInvestmentLogId(Long investmentLogId) { this.investmentLogId = investmentLogId; }

    public LocalDate getContributionDate() { return contributionDate; }
    public void setContributionDate(LocalDate contributionDate) { this.contributionDate = contributionDate; }

    public double getContributionAmount() { return contributionAmount; }
    public void setContributionAmount(double contributionAmount) { this.contributionAmount = contributionAmount; }

    @Override
    public String toString() {
        return "InvestmentContribution{" +
                "id=" + id +
                ", investmentLogId=" + investmentLogId +
                ", contributionDate=" + contributionDate +
                ", contributionAmount=" + contributionAmount +
                '}';
    }
}
