package edu.fin.models.loan;

public class LoanSummary {
    private double remainingBalance;
    private double totalInterestPaid;
    private int adjustedLoanTerm;

    public double getRemainingBalance() { return this.remainingBalance;}
    public void setRaminingBalance(double remainingBalance) { this.remainingBalance = remainingBalance; }

    public double getTotalInterestPaid() { return this.totalInterestPaid;}
    public void setTotalInterestPaid(double totalInterestPaid) { this.totalInterestPaid = totalInterestPaid; }

    public int getAdjustedLoanTerm() { return this.adjustedLoanTerm;}
    public void setAdjustedLoanTerm(int adjustedLoanTerm) { this.adjustedLoanTerm = adjustedLoanTerm; }

    @Override
    public String toString() {
        return "LoanSummary{" +
                "remainingBalance=" + remainingBalance +
                ", totalInterestPaid=" + totalInterestPaid +
                ", adjustedLoanTerm=" + adjustedLoanTerm +
                '}';
    }
}