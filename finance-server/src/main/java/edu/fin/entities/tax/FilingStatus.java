package edu.fin.entities.tax;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FilingStatus {
	@JsonProperty("standard_deduction")
	private Integer standardDeduction;
	
	@JsonProperty("personal_exemption")
	private Deduction personalExemption;
	
	@JsonProperty("dependent_exemption")
	private Deduction dependentExemption;
	
	@JsonProperty("income_tax_brackets")
	private List<IncomeTaxBracket> incomeTaxBrackets;
	
	public Integer getStandardDeduction() {
		return standardDeduction;
	}
	public void setStandardDeduction(Integer standardDeduction) {
		this.standardDeduction = standardDeduction;
	}
	public Deduction getPersonalExemption() {
		return personalExemption;
	}
	public void setPersonalExemption(Deduction personalExemption) {
		this.personalExemption = personalExemption;
	}
	public Deduction getDependentExemption() {
		return dependentExemption;
	}
	public void setDependentExemption(Deduction dependentExemption) {
		this.dependentExemption = dependentExemption;
	}
	public List<IncomeTaxBracket> getIncomeTaxBrackets() {
		return incomeTaxBrackets;
	}
	public void setIncomeTaxBrackets(List<IncomeTaxBracket> incomeTaxBrackets) {
		this.incomeTaxBrackets = incomeTaxBrackets;
	}
}
