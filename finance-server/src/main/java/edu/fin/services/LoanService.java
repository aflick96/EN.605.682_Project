package edu.fin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fin.dtos.loan.LoanItemRequest;
import edu.fin.dtos.loan.LoanPaymentRequest;
import edu.fin.entities.loan.*;
import edu.fin.entities.user.User;
import edu.fin.repositories.loan.LoanItemRepository;
import edu.fin.repositories.loan.LoanPaymentRepository;
import edu.fin.repositories.user.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {
	@Autowired
	private LoanItemRepository loanItemRepository;

	@Autowired
	private LoanPaymentRepository loanPaymentRepository;

	@Autowired
	private UserRepository userRepository;

	// Return the loan items for a user
	public List<LoanItemRequest> getLoanItems(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return null;

		List<LoanItem> items = loanItemRepository.findByUserId(userId);
		List<LoanItemRequest> req_items = items.stream().map(item -> {
			return new LoanItemRequest(
				item.getId(),
				item.getName(),
				item.getItemValue(),
				item.getLoanAmount(),
				item.getInterestRate(),
				item.getStartDate(),
				item.getLoanTermMonths()
			);
		}).toList();

		return req_items;
	}

	// Create a new loan item for a user
	public void createLoanItem(Long userId, LoanItemRequest req_item) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return;

		LoanItem item = new LoanItem();
		item.setUser(user);
		item.setName(req_item.getName());
		item.setItemValue(req_item.getItemValue());
		item.setLoanAmount(req_item.getLoanAmount());
		item.setInterestRate(req_item.getInterestRate());
		item.setStartDate(req_item.getStartDate());
		item.setLoanTermMonths(req_item.getLoanTermMonths());
		item.setLoanPayments(List.of());

		loanItemRepository.save(item);
	}

	// Get a loan item by ID
	public LoanItemRequest getLoanItemById(Long userId, Long itemId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return null;

		LoanItem loanItem = loanItemRepository.findById(itemId).orElse(null);
		if (loanItem == null) return null;

		return new LoanItemRequest(
			loanItem.getId(),
			loanItem.getName(),
			loanItem.getItemValue(),
			loanItem.getLoanAmount(),
			loanItem.getInterestRate(),
			loanItem.getStartDate(),
			loanItem.getLoanTermMonths()
		);
	}

	public void addPaymentToLoanItem(Long userId, Long itemId, LoanPaymentRequest payment) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return;

		// Get the loan item by id
		LoanItem loanItem = loanItemRepository.findById(itemId).orElse(null);
		if (loanItem == null) return;

		// create a new loan payment entity and set its properties
		LoanPayment loanPayment = new LoanPayment();
		loanPayment.setLoanItem(loanItem);
		loanPayment.setPaymentDate(payment.getPaymentDate());
		loanPayment.setPaymentAmount(payment.getPaymentAmount());

		List<LoanPayment> previousPayments = loanPaymentRepository.findByLoanItemIdOrderByPaymentDateAsc(loanItem.getId());
		populatePaymentBreakdown(loanItem, previousPayments, loanPayment);
		
		// save the payment to the database
		loanPaymentRepository.save(loanPayment);
		loanItem.getLoanPayments().add(loanPayment);
		loanItemRepository.save(loanItem);
	}

	//Get all payments for a specific loan item
	public List<LoanPaymentRequest> getLoanPaymentsByItemId(Long userId, Long loanItemId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return null;

		LoanItem loanItem = loanItemRepository.findById(loanItemId).orElse(null);
		if (loanItem == null) return null;

		List<LoanPayment> payments = loanPaymentRepository.findByLoanItemIdOrderByPaymentDateAsc(loanItemId);
		List<LoanPaymentRequest> req_payments = payments.stream().map(payment -> {
			return new LoanPaymentRequest(
				payment.getId(),
				loanItemId,
				payment.getPaymentDate(),
				payment.getPaymentAmount()
			);
		}).toList();

		return req_payments;
	}

	// Update the loan payments associated with a loan item
	public void updateLoanPayments(Long userId, Long loanItemId, List<LoanPaymentRequest> payments) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return;

		LoanItem loanItem = loanItemRepository.findById(loanItemId).orElse(null);
		if (loanItem == null) return;

		List<LoanPayment> existingPayments = loanPaymentRepository.findByLoanItemIdOrderByPaymentDateAsc(loanItemId);
		for (LoanPayment existingPayment : existingPayments) {
			for (LoanPaymentRequest payment : payments) {
				if (existingPayment.getId().equals(payment.getId())) {
					existingPayment.setPaymentDate(payment.getPaymentDate());
					existingPayment.setPaymentAmount(payment.getPaymentAmount());
					populatePaymentBreakdown(loanItem, existingPayments, existingPayment);
					loanPaymentRepository.save(existingPayment);
				}
			}
		}
	}

	// Delete a loan payment by ID
	public void deleteLoanItemById(Long userId, Long loanItemId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) return;

		LoanItem loanItem = loanItemRepository.findById(loanItemId).orElse(null);
		if (loanItem == null) return;

		loanItemRepository.delete(loanItem);
	}

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
