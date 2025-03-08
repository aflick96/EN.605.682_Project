package edu.fin.repositories.property;

import edu.fin.models.property.LoanItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanItemRepository extends JpaRepository<LoanItem, Long> {
	List<LoanItem>findByUserId(Long userId);
} 
