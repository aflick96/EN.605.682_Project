/*
 * LoanCompletionData.java
 * 
 * This class represents the data for loan completion. It contains information about the loan name, total amount, amount paid, remaining amount, and percentage completed.
 */
package edu.fin.dtos.dashboard;

public class LoanCompletionData {
    private String loanName;
    private double totalAmount;
    private double amountPaid;
    private double remainingAmount;
    private double percentageCompleted;

    // constructors
    public LoanCompletionData() {}
    public LoanCompletionData(String loanName, double totalAmount, double amountPaid, double remainingAmount, double percentageCompleted) {
        this.loanName = loanName;
        this.totalAmount = totalAmount;
        this.amountPaid = amountPaid;
        this.remainingAmount = remainingAmount;
        this.percentageCompleted = percentageCompleted;
    }

    // getters and setters
    public String getLoanName() { return loanName; }
    public void setLoanName(String loanName) { this.loanName = loanName; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(double amountPaid) { this.amountPaid = amountPaid; }

    public double getRemainingAmount() { return remainingAmount; }
    public void setRemainingAmount(double remainingAmount) { this.remainingAmount = remainingAmount; }

    public double getPercentageCompleted() { return percentageCompleted; }
    public void setPercentageCompleted(double percentageCompleted) { this.percentageCompleted = percentageCompleted; }

    @Override
    public String toString() {
        return "LoanCompletionData{" +
                "loanName='" + loanName + '\'' +
                ", totalAmount=" + totalAmount +
                ", amountPaid=" + amountPaid +
                ", remainingAmount=" + remainingAmount +
                ", percentageCompleted=" + percentageCompleted +
                '}';
    }
}
