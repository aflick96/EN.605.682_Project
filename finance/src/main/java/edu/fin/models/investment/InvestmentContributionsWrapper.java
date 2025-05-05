/*
 * InvestmentContributionsWrapper.java
 * 
 * This class is a wrapper for a list of InvestmentContribution objects.
 */
package edu.fin.models.investment;

import java.util.List;

public class InvestmentContributionsWrapper {
    private List<InvestmentContribution> investmentContributions;

    // constructors
    public InvestmentContributionsWrapper() {}
    public List<InvestmentContribution> getInvestmentContributions() {
        return investmentContributions;
    }

    // setter
    public void setInvestmentContributions(List<InvestmentContribution> investmentContributions) {
        this.investmentContributions = investmentContributions;
    }
}
