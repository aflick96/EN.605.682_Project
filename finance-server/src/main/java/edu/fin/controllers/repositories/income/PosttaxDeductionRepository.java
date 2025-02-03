package edu.fin.controllers.repositories.income;

import edu.fin.models.income.PosttaxDeduction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PosttaxDeductionRepository extends JpaRepository<PosttaxDeduction, Long> {
	List<PosttaxDeduction> findByIncomeLogId(Long incomeLogId);
}
