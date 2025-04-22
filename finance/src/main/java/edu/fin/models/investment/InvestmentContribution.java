package edu.fin.models.investment;

import java.time.LocalDate;

public class InvestmentContribution {
    private Long investmentLogId;
    private LocalDate contributionDate;
    private double contributionAmount;

    public Long getInvestmentLogId() { return investmentLogId; }
    public void setInvestmentLogId(Long investmentLogId) { this.investmentLogId = investmentLogId; }

    public LocalDate getContributionDate() { return contributionDate; }
    public void setContributionDate(LocalDate contributionDate) { this.contributionDate = contributionDate; }

    public double getContributionAmount() { return contributionAmount; }
    public void setContributionAmount(double contributionAmount) { this.contributionAmount = contributionAmount; }

    @Override
    public String toString() {
        return "InvestmentContribution{" +
                "investmentLogId=" + investmentLogId +
                ", contributionDate=" + contributionDate +
                ", contributionAmount=" + contributionAmount +
                '}';
    }
}
