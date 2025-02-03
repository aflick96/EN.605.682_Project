package edu.fin.models.income;

import edu.fin.models.user.User;
import edu.fin.models.income.enums.PayFrequency;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "income_logs")
public class IncomeLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	@Enumerated(EnumType.STRING)
	private PayFrequency annualPayFrequency;
	
	@OneToMany(mappedBy="incomeLog", cascade=CascadeType.ALL)
	private List<PretaxDeduction> pretaxDeductions;
	
	@OneToMany(mappedBy="incomeLog", cascade=CascadeType.ALL)
	private List<PosttaxDeduction> posttaxDeductions;

	@OneToMany(mappedBy="incomeLog", cascade=CascadeType.ALL)
	private List<AdditionalIncome> additionalIncomes;

	public Long getIncomeLogId() {
		return id;
	}

	public void setIncomeLogId(Long incomeLogId) {
		this.id = incomeLogId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public PayFrequency getAnnualPayFrequency() {
		return annualPayFrequency;
	}

	public void setAnnualPayFrequency(PayFrequency annualPayFrequency) {
		this.annualPayFrequency = annualPayFrequency;
	}

	public List<PretaxDeduction> getPretaxDeductions() {
		return pretaxDeductions;
	}

	public void setPretaxDeductions(List<PretaxDeduction> pretaxDeductions) {
		this.pretaxDeductions = pretaxDeductions;
	}

	public List<PosttaxDeduction> getPosttaxDeductions() {
		return posttaxDeductions;
	}

	public void setPosttaxDeductions(List<PosttaxDeduction> posttaxDeductions) {
		this.posttaxDeductions = posttaxDeductions;
	}

	public List<AdditionalIncome> getAdditionalIncomes() {
		return additionalIncomes;
	}

	public void setAdditionalIncomes(List<AdditionalIncome> additionalIncomes) {
		this.additionalIncomes = additionalIncomes;
	}
}
