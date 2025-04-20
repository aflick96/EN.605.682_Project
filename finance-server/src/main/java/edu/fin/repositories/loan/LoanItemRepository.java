package edu.fin.repositories.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fin.entities.loan.LoanItem;

import java.util.List;

public interface LoanItemRepository extends JpaRepository<LoanItem, Long> {
	List<LoanItem>findByUserId(Long userId);
} 
