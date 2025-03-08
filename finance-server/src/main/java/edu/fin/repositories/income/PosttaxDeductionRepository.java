package edu.fin.repositories.income;

import edu.fin.models.income.PosttaxDeduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface PosttaxDeductionRepository extends JpaRepository<PosttaxDeduction, Long> {
	List<PosttaxDeduction> findByIncomeLogId(Long incomeLogId);

	@Transactional
	void deleteByIncomeLogId(Long incomeLogId);
}
