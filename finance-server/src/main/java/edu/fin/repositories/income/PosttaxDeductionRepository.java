package edu.fin.repositories.income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import edu.fin.entities.income.PosttaxDeduction;

import java.util.List;

public interface PosttaxDeductionRepository extends JpaRepository<PosttaxDeduction, Long> {
	List<PosttaxDeduction> findByIncomeLogId(Long incomeLogId);

	@Transactional
	void deleteByIncomeLogId(Long incomeLogId);
}
