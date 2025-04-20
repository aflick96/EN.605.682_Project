package edu.fin.entities.income.enums;

public enum PayFrequency {
	WEEKLY(52),
	BIWEEKLY(26),
	SEMIMONTHLY(24),
	MONTHLY(12);
	
	private final int periods;
	
	PayFrequency(int periods) {
		this.periods = periods;
	}
	
	public int getPeriods() {
		return periods;
	}
}
