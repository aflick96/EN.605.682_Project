package edu.fin.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import edu.fin.models.investment.InvestmentContribution;
import edu.fin.models.investment.InvestmentLog;
import edu.fin.models.investment.WhatIfScenarioRow;

@Service
public class InvestmentService {
    public List<WhatIfScenarioRow> computeScenarioTable(InvestmentLog investment, InvestmentContribution[] realContributions, double userWeeklyContribution) {
        LocalDate start = investment.getStartDate();
        LocalDate end = investment.getEndDate() != null ? investment.getEndDate() : start.plusYears(1);
        double annualReturn = (investment.getExpectedAnnualReturn() != null)
                            ? investment.getExpectedAnnualReturn() / 100.0
                            : 0.0;

        // Convert annual return to a weekly return (approx).
        // e.g. 10% annual => ~0.1/52 => 0.00192
        double weeklyReturnRate = annualReturn / 52.0;

        // Turn realContributions into a map of date -> sum of contributions that day
        Map<LocalDate, Double> realMap = new HashMap<>();
        for (InvestmentContribution c : realContributions) {
            // Only count if c.getContributionDate() is within [start, end].
            LocalDate cd = c.getContributionDate();
            if (!cd.isBefore(start) && !cd.isAfter(end)) {
                realMap.merge(cd, c.getContributionAmount(), Double::sum);
            }
        }

        List<WhatIfScenarioRow> rows = new ArrayList<>();
        double runningBalance = 0.0;
        double runningGrowth = 0.0;

        // Iterate weekly from start to end
        LocalDate current = start;
        while (!current.isAfter(end)) {
            WhatIfScenarioRow row = new WhatIfScenarioRow();
            row.setWeekStart(current);

            // Real contributions on this date
            double realContribThisWeek = realMap.getOrDefault(current, 0.0);
            // Scenario contribution
            //   - If you only want to apply userWeeklyContribution for future weeks,
            //     check if current date >= LocalDate.now()
            double scenarioContribThisWeek = userWeeklyContribution;

            double totalContribThisWeek = realContribThisWeek + scenarioContribThisWeek;
            // Increase running balance by this week's contributions
            runningBalance += totalContribThisWeek;

            // Earn weekly growth on the new balance
            // a very rough approach:
            double growthThisWeek = runningBalance * weeklyReturnRate;
            runningGrowth += growthThisWeek;
            runningBalance += growthThisWeek; // add growth to the balance

            // Fill row
            row.setRealContribution(realContribThisWeek);
            row.setScenarioContribution(scenarioContribThisWeek);
            row.setTotalContributions(runningBalance - runningGrowth); // the portion that's from contributions
            row.setGrowthThisWeek(growthThisWeek);
            row.setTotalGrowth(runningGrowth);
            row.setEndBalance(runningBalance);

            rows.add(row);
            current = current.plusWeeks(1);
        }
        return rows;
    }
    
}
