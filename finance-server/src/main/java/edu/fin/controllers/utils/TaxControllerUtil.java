package edu.fin.controllers.utils;

import edu.fin.models.tax.FilingStatus;
import edu.fin.models.tax.FederalTax;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public class TaxControllerUtil {
	public Map<String, Object> filterStateFilingStatus(FilingStatus filingStatus, String select) {
		final String sd = "standard_deduction";
		final String pe = "personal_exemption";
		final String de = "dependent_exemption";
		final String tb = "income_tax_brackets";
		
		Map<String, Object> filteredData = new LinkedHashMap<>();
		
		if (filingStatus == null) {
			return null;
		}
		
		if (select == null) {
			filteredData.put(sd, filingStatus.getStandardDeduction());
			filteredData.put(pe, filingStatus.getPersonalExemption());
			filteredData.put(de, filingStatus.getDependentExemption());
			filteredData.put(tb, filingStatus.getIncomeTaxBrackets());
			return filteredData;
		} 
		
		List<String> selectedFields = Arrays.asList(select.split(","));
		
		if (selectedFields.contains(sd)) {
			filteredData.put(sd, filingStatus.getStandardDeduction());			
		}
		
		if (selectedFields.contains(pe)) {
			filteredData.put(pe, filingStatus.getPersonalExemption());			
		}
		
		if (selectedFields.contains(de)) {
			filteredData.put(de, filingStatus.getDependentExemption());			
		}
		
		if (selectedFields.contains(tb)) {
			filteredData.put(tb, filingStatus.getIncomeTaxBrackets());			
		}

		return filteredData;
	}
	
	public Object filterFederalFilingStatus(FederalTax.FilingStatus filingStatus, String select) {
        if (select == null) {
            return filingStatus;
        }

        String[] fields = select.split(",");
        Map<String, Object> filteredData = new LinkedHashMap<>();

        for (String field : fields) {
            switch (field.trim()) {
                case "standard_deduction":
                    filteredData.put("standard_deduction", filingStatus.getStandardDeduction());
                    break;
                case "income_tax_brackets":
                    filteredData.put("income_tax_brackets", filingStatus.getIncomeTaxBrackets());
                    break;
                default:
                    break;
            }
        }

        return filteredData;
    }
}
