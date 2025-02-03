package edu.fin.services;

import edu.fin.models.property.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoanService {
	
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
}
