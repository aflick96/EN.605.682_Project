package edu.fin.models.income;

import jakarta.persistence.*;

@Entity
@Table(name="additional_income")
public class AdditionalIncome {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long additionalIncomeId;
	
	private String description;
	private Double amount;
	
	@ManyToOne
	@JoinColumn(name="income_log_id", nullable=false)
	private IncomeLog incomeLog;

	public Long getAdditionalIncomeId() {
		return additionalIncomeId;
	}

	public void setAdditionalIncomeId(Long additionalIncomeId) {
		this.additionalIncomeId = additionalIncomeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
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
