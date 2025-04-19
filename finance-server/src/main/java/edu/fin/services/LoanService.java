package edu.fin.services;

import edu.fin.models.property.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {
	
	public void populatePaymentBreakdown(LoanItem loanItem, List<LoanPayment> previousPayments, LoanPayment newPayment) {
		double remainingBalance = calculateRemainingBalance(loanItem, previousPayments);
		double annualInterestRate = loanItem.getInterestRate() / 100.0;
		double monthlyInterestRate = annualInterestRate / 12.0;

		int monthsSinceLastPayment = 1;

		if (!previousPayments.isEmpty()) {
			LoanPayment lastPayment = previousPayments.get(previousPayments.size() - 1);
			LocalDate lastDate = lastPayment.getPaymentDate();
			LocalDate currentDate = newPayment.getPaymentDate();

			if (lastDate != null && currentDate != null) {
				monthsSinceLastPayment = Math.max(1, currentDate.getMonthValue() - lastDate.getMonthValue() + 12 * (currentDate.getYear() - lastDate.getYear()));
			}
		}

		double interestPaid = remainingBalance * monthlyInterestRate * monthsSinceLastPayment;
		double principalPaid = newPayment.getPaymentAmount() - interestPaid;
		if (principalPaid < 0) {
			principalPaid = 0;
		}

		newPayment.setInterestPaid(round(interestPaid));
		newPayment.setPrincipalPaid(round(principalPaid));
	}

	public double calculateRemainingBalance(LoanItem loanItem, List<LoanPayment> payments) {
		double balance = loanItem.getLoanAmount();
		for (LoanPayment payment: payments) {
			balance -= payment.getPrincipalPaid();
		}
		return balance;
	}
	
	public double calculateTotalInterestPaid(List<LoanPayment> payments) {
		return payments.stream().
				mapToDouble(LoanPayment::getInterestPaid)
				.sum();
	}
	
	public int calculateAdjustedLoanTerm(LoanItem loanItem, List<LoanPayment> payments) {
		double balance = calculateRemainingBalance(loanItem, payments);
		double monthlyInterestRate = loanItem.getInterestRate() / 12 / 100;
		double monthlyPayment = payments.isEmpty() ? 0 : payments.get(payments.size() - 1).getPaymentAmount();
		
		if (monthlyPayment == 0 || balance <= 0) {
			return 0;
		}
		
        return (int) Math.ceil(Math.log(1 + (balance * monthlyInterestRate) / monthlyPayment) /
                Math.log(1 + monthlyInterestRate));
	}

	private double round(double value) {
		return Math.round(value * 100.0) / 100.0;
	}
}
