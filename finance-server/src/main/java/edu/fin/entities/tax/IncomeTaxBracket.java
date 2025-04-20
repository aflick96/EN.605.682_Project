package edu.fin.entities.tax;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncomeTaxBracket {
	@JsonProperty("rate")
	private Double rate;
	@JsonProperty("bracket")
	private Integer bracket;
	
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getBracket() {
		return bracket;
	}
	public void setBracket(Integer bracket) {
		this.bracket = bracket;
	}
}

