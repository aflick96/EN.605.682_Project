/*
 * InvestmentContributionsRequest.java
 * 
 * This class represents a request to add multiple contributions to an investment log. It contains the start date, end date, contribution amount, contribution day, specific day, and investment log ID.
 */
package edu.fin.dtos.investment;

public class InvestmentContributionsRequest {
    private String startDate;
    private String endDate;
    private double contributionAmount;
    private String contributionDay; 
    private Integer specificDay;
    private Long investmentLogId;

    // constructors
    public InvestmentContributionsRequest() {}
    public InvestmentContributionsRequest(String startDate, String endDate, double contributionAmount, String contributionDay, Integer specificDay, Long investmentLogId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.contributionAmount = contributionAmount;
        this.contributionDay = contributionDay;
        this.specificDay = specificDay;
        this.investmentLogId = investmentLogId;
    }

    // getters and setters
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
