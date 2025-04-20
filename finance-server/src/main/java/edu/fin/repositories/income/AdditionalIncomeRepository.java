package edu.fin.repositories.income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import edu.fin.entities.income.AdditionalIncome;

import java.util.List;

public interface AdditionalIncomeRepository extends JpaRepository<AdditionalIncome, Long> {
	List<AdditionalIncome> findByIncomeLogId(Long incomeLogId);

	@Transactional
	void deleteByIncomeLogId(Long incomeLogId);
}
