package edu.fin.entities.income;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import edu.fin.entities.income.enums.PayFrequency;
import edu.fin.entities.user.User;

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

	@Column(name="amount", nullable=false)
	private Double amount;
	
	@Enumerated(EnumType.STRING)
	private PayFrequency annualPayFrequency;
	
	@OneToMany(mappedBy="incomeLog", cascade=CascadeType.ALL)
	private List<PretaxDeduction> pretaxDeductions;
	
	@OneToMany(mappedBy="incomeLog", cascade=CascadeType.ALL)
	private List<PosttaxDeduction> posttaxDeductions;

	@OneToMany(mappedBy="incomeLog", cascade=CascadeType.ALL)
	private List<AdditionalIncome> additionalIncomes;

	public Long getIncomeLogId() { return id; }
	public void setIncomeLogId(Long incomeLogId) { this.id = incomeLogId; }

	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

	public LocalDate getStartDate() { return startDate; }
	public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

	public LocalDate getEndDate() { return endDate; }
	public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

	public void setAmount(Double amount) { this.amount = amount; }
	public Double getAmount() { return amount; }

	public PayFrequency getAnnualPayFrequency() { return annualPayFrequency; }
	public void setAnnualPayFrequency(PayFrequency annualPayFrequency) { this.annualPayFrequency = annualPayFrequency; }

	public List<PretaxDeduction> getPretaxDeductions() { return pretaxDeductions; }
	public void setPretaxDeductions(List<PretaxDeduction> pretaxDeductions) { this.pretaxDeductions = pretaxDeductions; }

	public List<PosttaxDeduction> getPosttaxDeductions() { return posttaxDeductions; }
	public void setPosttaxDeductions(List<PosttaxDeduction> posttaxDeductions) { this.posttaxDeductions = posttaxDeductions; }

	public List<AdditionalIncome> getAdditionalIncomes() { return additionalIncomes; }
	public void setAdditionalIncomes(List<AdditionalIncome> additionalIncomes) { this.additionalIncomes = additionalIncomes; }

	@Override
	public String toString() {
		return "IncomeLog [incomeLogId=" + id + ", user=" + user + ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount
				+ ", annualPayFrequency=" + annualPayFrequency + ", pretaxDeductions=" + pretaxDeductions
				+ ", posttaxDeductions=" + posttaxDeductions + ", additionalIncomes=" + additionalIncomes + "]";
	}
}
