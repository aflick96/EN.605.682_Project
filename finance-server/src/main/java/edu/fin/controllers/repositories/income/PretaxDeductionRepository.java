package edu.fin.controllers.repositories.income;

import edu.fin.models.income.PretaxDeduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface PretaxDeductionRepository extends JpaRepository<PretaxDeduction, Long> {
	List<PretaxDeduction> findByIncomeLogId(Long incomeLogId);

	@Transactional
	void deleteByIncomeLogId(Long incomeLogId);
}
