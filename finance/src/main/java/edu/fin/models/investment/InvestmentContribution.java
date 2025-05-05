/*
 * InvestmentContribution.java
 * 
 * This class represents an investment contribution made by a user.
 */
package edu.fin.models.investment;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class InvestmentContribution {
    private Long id;
    private Long investmentLogId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate contributionDate;
    private double contributionAmount;

    // constructors
    public InvestmentContribution() {}
    public InvestmentContribution(Long id, Long investmentLogId, LocalDate contributionDate, double contributionAmount) {
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
