/*
 * InvestmentSummary.java
 * 
 * This class represents a summary of an investment. It contains the current value of the investment and the total contributions made to it.
 */
package edu.fin.dtos.investment;

public class InvestmentSummary {
	private double currentValue;
	private double totalContributions;
	
	// constructors
	public InvestmentSummary() {}
	public InvestmentSummary(double currentValue, double totalContributions) {
		this.currentValue = currentValue;
		this.totalContributions = totalContributions;
	}

	// getters and setters
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
