package edu.fin.models.income;

import edu.fin.models.income.enums.PretaxDeductionType;
import jakarta.persistence.*;

@Entity
@Table(name="pretax_deductions")
public class PretaxDeduction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pretaxDeductionId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private PretaxDeductionType description;
	
	private Double amount;
	
	@ManyToOne
	@JoinColumn(name="income_log_id", nullable=false)
	private IncomeLog incomeLog;

	public Long getPretaxDeductionId() {
		return pretaxDeductionId;
	}

	public void setPretaxDeductionId(Long pretaxDeductionId) {
		this.pretaxDeductionId = pretaxDeductionId;
	}

	public PretaxDeductionType getDescription() {
		return description;
	}

	public void setDescription(PretaxDeductionType description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public IncomeLog getIncomeLog() {
		return incomeLog;
	}

	public void setIncomeLog(IncomeLog incomeLog) {
		this.incomeLog = incomeLog;
	}
}
