package edu.fin.entities.tax;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Deduction {
	@JsonProperty("amount")
	private Integer amount;
	
	@JsonProperty("type")
	private String type;
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

