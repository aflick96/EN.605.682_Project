package edu.fin.services;

import edu.fin.models.investment.InvestmentLog;
import edu.fin.models.investment.InvestmentContribution;
import edu.fin.models.investment.WhatIfScenarioRow;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class InvestmentService {
    public List<WhatIfScenarioRow> computeInvestmentScenarioTable(InvestmentLog investment, InvestmentContribution[] contributions, double scenarioContributions, double expectedReturn) {
        // Base fields
        LocalDate start = investment.getStartDate();
        LocalDate end = investment.getEndDate() != null ? investment.getEndDate() : start.plusYears(1);
        LocalDate current = start;
        double annualReturnRate = (expectedReturn > 0.0) ? expectedReturn / 100.0 : (investment.getExpectedAnnualReturn() != null) ? investment.getExpectedAnnualReturn() / 100.0 : 0.0;
        double weeklyReturnRate = annualReturnRate / 52.0;
        double runningBalance = 0.0;
        double runningGrowth = 0.0;
        List<WhatIfScenarioRow> rows = new ArrayList<>();

        // Create map of date -> sum of contributions that day
        Map<LocalDate, Double> contributionMap = new HashMap<>();
        for (InvestmentContribution c : contributions) {
            LocalDate contributionDate = c.getContributionDate();
            if (!contributionDate.isBefore(start) && !contributionDate.isAfter(end)) {
                contributionMap.merge(contributionDate, c.getContributionAmount(), Double::sum);
            }
        }

        while(!current.isAfter(end)) {
            WhatIfScenarioRow row = new WhatIfScenarioRow();

            // Get contributions for each week            
            double contributionThisWeek = 0.0;
            for (Map.Entry<LocalDate, Double> e: contributionMap.entrySet()) {
                LocalDate date = e.getKey();
                if (!date.isBefore(current) && date.isBefore(current.plusWeeks(1))) {
                    contributionThisWeek += e.getValue();
                }
            }

            // roughly calculate weekly row values
            double growthThisWeek = 0.0;
            double totalContributionThisWeek = contributionThisWeek + scenarioContributions;
            runningBalance += totalContributionThisWeek;
            growthThisWeek = runningBalance * weeklyReturnRate;
            runningGrowth += growthThisWeek;
            runningBalance += growthThisWeek;

            // Fill row
            row.setWeekStart(current);
            row.setRealContribution(contributionThisWeek);
            row.setScenarioContribution(scenarioContributions);
            row.setTotalContributions(runningBalance - runningGrowth);
            row.setGrowthThisWeek(growthThisWeek);
            row.setTotalGrowth(runningGrowth);
            row.setEndBalance(runningBalance);
            rows.add(row);

            current = current.plusWeeks(1);
        }
        
        return rows;
    }    
}
