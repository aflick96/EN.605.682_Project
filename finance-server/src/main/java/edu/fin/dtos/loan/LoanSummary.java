/*
 * LoanSummary.java
 * 
 * This class represents a summary of a loan. It contains the remaining balance, total interest paid, and the adjusted loan term.
 */
package edu.fin.dtos.loan;

public class LoanSummary {
	private double remainingBalance;
	private double totalInterestPaid;
	private int adjustedLoanTerm;
	
	// constructors
	public LoanSummary() {}
	public LoanSummary(double remainingBalance, double totalInterestPaid, int adjustedLoanTerm) {
		this.remainingBalance = remainingBalance;
		this.totalInterestPaid = totalInterestPaid;
		this.adjustedLoanTerm = adjustedLoanTerm;
	}

	// getters and setters
	public double getRemainingBalance() {
		return remainingBalance;
	}

	public void setRemainingBalance(double remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	public double getTotalInterestPaid() {
		return totalInterestPaid;
	}

	public void setTotalInterestPaid(double totalInterestPaid) {
		this.totalInterestPaid = totalInterestPaid;
	}

	public int getAdjustedLoanTerm() {
		return adjustedLoanTerm;
	}

	public void setAdjustedLoanTerm(int adjustedLoanTerm) {
		this.adjustedLoanTerm = adjustedLoanTerm;
	}
}
