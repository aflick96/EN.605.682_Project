package edu.fin.utils.income;

import org.springframework.stereotype.Component;

import edu.fin.entities.income.enums.PretaxDeductionType;

@Component
public class IncomeUtil {
	public boolean isValidDeductionType(String deduction) {
		try {
			PretaxDeductionType.valueOf(deduction.toUpperCase());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
