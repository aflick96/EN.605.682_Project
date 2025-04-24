package edu.fin.models.investment;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class InvestmentContribution {
    private Long id;
    private Long investmentLogId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contributionDate;
    private double contributionAmount;

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
                "investmentLogId=" + investmentLogId +
                ", contributionDate=" + contributionDate +
                ", contributionAmount=" + contributionAmount +
                '}';
    }
}
