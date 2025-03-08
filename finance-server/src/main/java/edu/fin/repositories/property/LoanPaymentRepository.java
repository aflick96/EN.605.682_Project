package edu.fin.repositories.property;

import edu.fin.models.property.LoanPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanPaymentRepository extends JpaRepository<LoanPayment, Long> {
	List<LoanPayment> findByLoanItemId(Long loanItemId);
}
