package edu.fin.models.property;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="loan_payments")
public class LoanPayment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="loan_item_id", nullable=false)
	private LoanItem loanItem;
	
	private LocalDate paymentDate;
	private Double paymentAmount;
	private Double principalPaid;
	private Double interestPaid;
	
	public Long getLoanPaymentId() {
		return id;
	}
	
	public void setLoanPaymentId(Long loanPaymentId) {
		this.id = loanPaymentId;
	}
	
	public LoanItem getLoanItem() {
		return loanItem;
	}
	public void setLoanItem(LoanItem loanItem) {
		this.loanItem = loanItem;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public Double getPrincipalPaid() {
		return principalPaid;
	}
	public void setPrincipalPaid(Double principalPaid) {
		this.principalPaid = principalPaid;
	}
	public Double getInterestPaid() {
		return interestPaid;
	}
	public void setInterestPaid(Double interestPaid) {
		this.interestPaid = interestPaid;
	}
}
