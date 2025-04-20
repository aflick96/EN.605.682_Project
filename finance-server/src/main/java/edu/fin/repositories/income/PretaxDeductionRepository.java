package edu.fin.repositories.income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import edu.fin.entities.income.PretaxDeduction;

import java.util.List;

public interface PretaxDeductionRepository extends JpaRepository<PretaxDeduction, Long> {
	List<PretaxDeduction> findByIncomeLogId(Long incomeLogId);

	@Transactional
	void deleteByIncomeLogId(Long incomeLogId);
}
