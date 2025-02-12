package edu.fin.controllers.repositories.income;

import edu.fin.models.income.AdditionalIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface AdditionalIncomeRepository extends JpaRepository<AdditionalIncome, Long> {
	List<AdditionalIncome> findByIncomeLogId(Long incomeLogId);

	@Transactional
	void deleteByIncomeLogId(Long incomeLogId);
}
