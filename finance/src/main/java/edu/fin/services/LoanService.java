package edu.fin.services;

import edu.fin.models.loan.LoanItem;
import edu.fin.models.loan.LoanPayment;
import edu.fin.models.loan.WhatIfScenarioRow;
import edu.fin.models.loan.LoanScenarioResult;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanService {
    public LoanScenarioResult computeLoanScenarioTable(LoanItem loanItem, LoanPayment[] payments, Double monthlyPayment, Double interestRate, Integer loanTerm) {        
        double monthlyInterestRate = (interestRate > 0.0) ? interestRate / 100.0 / 12.0 : (loanItem.getInterestRate() != null) ? loanItem.getInterestRate() / 100.0 / 12.0 : 0.0;    
        loanTerm = (loanTerm == 0) ? loanItem.getLoanTermMonths() : loanTerm;

        double scenarioPayment = monthlyPayment;
        if (scenarioPayment <= 0) {
            if (monthlyInterestRate > 0.0) {
                scenarioPayment = (loanItem.getLoanAmount() * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -loanTerm));
            } else {
                scenarioPayment = loanItem.getLoanAmount() / loanTerm;
            }
        }
        
        LocalDate start = loanItem.getStartDate();
        LocalDate end = start.plusMonths(loanTerm);
        LocalDate current = start;
        double runningBalance = loanItem.getLoanAmount();
        double runningInterest = 0.0;
        double runningPrincipal = 0.0;
        List<WhatIfScenarioRow> rows = new ArrayList<>();

        // Create map of date -> sum of payments that day
        Map<LocalDate, Double> paymentMap = new HashMap<>();
        for (LoanPayment p : payments) {
            LocalDate paymentDate = p.getPaymentDate();
            if (!paymentDate.isBefore(start) && !paymentDate.isAfter(end)) {
                paymentMap.merge(paymentDate, p.getPaymentAmount(), Double::sum);
            }
        }

        int monthsCount = 0;

        while(!current.isAfter(end)) {
            WhatIfScenarioRow row = new WhatIfScenarioRow();

            // Get payments for each month            
            double paymentThisMonth = 0.0;
            for (Map.Entry<LocalDate, Double> e: paymentMap.entrySet()) {
                LocalDate date = e.getKey();
                if (!date.isBefore(current) && date.isBefore(current.plusMonths(1))) {
                    paymentThisMonth += e.getValue();
                }
            }

            double totalPaymentThisMonth = paymentThisMonth + scenarioPayment;
            double interestThisMonth = runningBalance * monthlyInterestRate;
            double principalThisMonth = totalPaymentThisMonth - interestThisMonth;
            if (principalThisMonth > runningBalance) {
                principalThisMonth = runningBalance;
            }

            runningBalance -= principalThisMonth;
            runningInterest += interestThisMonth;
            runningPrincipal += principalThisMonth;

            // Fill row
            row.setMonthStartDate(current);
            row.setRealPayment(paymentThisMonth);
            row.setScenarioPayment(scenarioPayment);
            row.setPaymentThisMonth(totalPaymentThisMonth);
            row.setPrincipalThisMonth(principalThisMonth);
            row.setInterestThisMonth(interestThisMonth);
            row.setTotalPrincipal(runningPrincipal);
            row.setTotalInterest(runningInterest);
            row.setTotalPaid(runningPrincipal + runningInterest);
            row.setPrincipalRemaining(runningBalance);
            row.setEndBalance(runningBalance + interestThisMonth);

            rows.add(row);
            current = current.plusMonths(1);

            if (runningBalance <= 0.0) {
                break;
            }

            monthsCount++;
        }
        return new LoanScenarioResult(scenarioPayment, monthsCount, rows);
    }
}
