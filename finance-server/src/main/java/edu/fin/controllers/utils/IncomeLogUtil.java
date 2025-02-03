package edu.fin.controllers.utils;

import edu.fin.models.income.enums.PretaxDeductionType;
import org.springframework.stereotype.Component;

@Component
public class IncomeLogUtil {
	public boolean isValidDeductionType(String deduction) {
		try {
			PretaxDeductionType.valueOf(deduction.toUpperCase());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
