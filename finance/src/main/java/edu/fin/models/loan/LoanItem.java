package edu.fin.models.loan;

public class LoanItem {

    private Long id;
    private String name;
    private Double itemValue;
    private Double loanAmount;
    private Double interestRate;
    private String startDate;
    private Integer loanTermMonths;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getItemValue() { return itemValue; }
    public void setItemValue(Double itemValue) { this.itemValue = itemValue; }

    public Double getLoanAmount() { return loanAmount;}
    public void setLoanAmount(Double loanAmount) {this.loanAmount = loanAmount; }

    public Double getInterestRate() {return interestRate;}
    public void setInterestRate(Double interestRate) {this.interestRate = interestRate;}

    public String getStartDate() {return startDate;}
    public void setStartDate(String startDate) {this.startDate = startDate;}

    public Integer getLoanTermMonths() {return loanTermMonths;}
    public void setLoanTermMonths(Integer loanTermMonths) {this.loanTermMonths = loanTermMonths;}

    @Override
    public String toString() {
        return "LoanItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", itemValue=" + itemValue +
                ", loanAmount=" + loanAmount +
                ", interestRate=" + interestRate +
                ", startDate='" + startDate + '\'' +
                ", loanTermMonths=" + loanTermMonths +
                '}';
    }
}
