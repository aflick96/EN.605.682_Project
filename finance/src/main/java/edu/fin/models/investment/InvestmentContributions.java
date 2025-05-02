/*
 * InvestmentContributions.java
 * 
 * This class represents a range of investment contributions.
 */

package edu.fin.models.investment;

public class InvestmentContributions {
    private String startDate;
    private String endDate;
    private double contributionAmount;
    private String contributionDay; 
    private Integer specificDay;
    private Long investmentLogId;

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public double getContributionAmount() { return contributionAmount; }
    public void setContributionAmount(double contributionAmount) { this.contributionAmount = contributionAmount; }

    public String getContributionDay() { return contributionDay; }
    public void setContributionDay(String contributionDay) { this.contributionDay = contributionDay; }

    public Integer getSpecificDay() { return specificDay; }
    public void setSpecificDay(Integer specificDay) { this.specificDay = specificDay; }

    public Long getInvestmentLogId() { return investmentLogId; }
    public void setInvestmentLogId(Long investmentLogId) { this.investmentLogId = investmentLogId; }

    @Override
    public String toString() {
        return "InvestmentContributions{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", contributionAmount=" + contributionAmount +
                ", contributionDay='" + contributionDay + '\'' +
                ", specificDay=" + specificDay +
                ", investmentLogId=" + investmentLogId +
                '}';
    }
}
