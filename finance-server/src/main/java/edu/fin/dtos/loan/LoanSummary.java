package edu.fin.dtos.loan;

public class LoanSummary {
	private double remainingBalance;
	private double totalInterestPaid;
	private int adjustedLoanTerm;
	
	public LoanSummary(double remainingBalance, double totalInterestPaid, int adjustedLoanTerm) {
		this.remainingBalance = remainingBalance;
		this.totalInterestPaid = totalInterestPaid;
		this.adjustedLoanTerm = adjustedLoanTerm;
	}

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
