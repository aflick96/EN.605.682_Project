package edu.fin.repositories.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fin.entities.loan.LoanPayment;

import java.util.List;

public interface LoanPaymentRepository extends JpaRepository<LoanPayment, Long> {
	List<LoanPayment> findByLoanItemId(Long loanItemId);
	List<LoanPayment> findByLoanItemIdOrderByPaymentDateAsc(Long loanItemId);
}
