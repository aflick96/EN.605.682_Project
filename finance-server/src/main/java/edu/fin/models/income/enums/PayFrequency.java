package edu.fin.models.income.enums;

public enum PayFrequency {
	FIFTY_TWO(52),
	TWENTY_SIX(26),
	TWENTY_FOUR(24),
	TWELVE(12);
	
	private final int periods;
	
	PayFrequency(int periods) {
		this.periods = periods;
	}
	
	public int getPeriods() {
		return periods;
	}
}
