package edu.fin.dtos.investment;

public class InvestmentSummary {
	private double currentValue;
	private double totalContributions;
	
	public InvestmentSummary(double currentValue, double totalContributions) {
		this.currentValue = currentValue;
		this.totalContributions = totalContributions;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	public double getTotalContributions() {
		return totalContributions;
	}

	public void setTotalContributions(double totalContributions) {
		this.totalContributions = totalContributions;
	}
}
