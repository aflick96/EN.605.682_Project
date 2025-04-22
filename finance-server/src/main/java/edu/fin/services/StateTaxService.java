package edu.fin.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import edu.fin.entities.tax.FilingStatus;
import edu.fin.entities.tax.IncomeTaxBracket;
import edu.fin.entities.tax.StateTax;
import edu.fin.entities.tax.StateTax.State;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class StateTaxService {
	private final ObjectMapper objectMapper;
	
	public StateTaxService() {
		this.objectMapper = new ObjectMapper();
		this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
	}
	
	public StateTax getStateTaxes() throws IOException {
		ClassPathResource resource = new ClassPathResource("state-taxes.json");
		try (InputStream input = resource.getInputStream()) {
			return objectMapper.readValue(input, StateTax.class);
		}		
	}

	public double calculateStateTax(double grossIncome, String stateAbbr, String filingStatus, int dependents) {
		try {
			// Get state tax data
			StateTax stateTaxData = getStateTaxes();
			State stateData = stateTaxData.getStates().get(stateAbbr.toLowerCase());
			if (stateData == null) return 0.0;

			// Get state-specific tax brackets
			FilingStatus stateBrackets = filingStatus.equalsIgnoreCase("married") ? stateData.getMarried() : stateData.getSingle();
			double standardDeduction = stateBrackets.getStandardDeduction();
			double taxableIncome = Math.max(0, grossIncome - standardDeduction);

			// Apply deductions
			if (stateBrackets.getPersonalExemption() != null && "deduction".equalsIgnoreCase(stateBrackets.getPersonalExemption().getType())) {
				taxableIncome = Math.max(0, taxableIncome - stateBrackets.getPersonalExemption().getAmount());
			}
			if (stateBrackets.getDependentExemption() != null && "deduction".equalsIgnoreCase(stateBrackets.getDependentExemption().getType())) {
				taxableIncome = Math.max(0, taxableIncome - (stateBrackets.getDependentExemption().getAmount() * dependents));
			}
	
			double totalTax = computeProgressiveTax(taxableIncome, stateBrackets.getIncomeTaxBrackets());
	
			// Apply credits
			if (stateBrackets.getPersonalExemption() != null && "credit".equalsIgnoreCase(stateBrackets.getPersonalExemption().getType())) {
				totalTax = Math.max(0, totalTax - stateBrackets.getPersonalExemption().getAmount());
			}
			if (stateBrackets.getDependentExemption() != null && "credit".equalsIgnoreCase(stateBrackets.getDependentExemption().getType())) {
				totalTax = Math.max(0, totalTax - (stateBrackets.getDependentExemption().getAmount() * dependents));
			}
	
			return totalTax;
		
		} catch (IOException e) {
			System.err.println("Error reading state tax data: " + e.getMessage());
			return 0.0;
		}
	}

	private double computeProgressiveTax(double taxableIncome, List<IncomeTaxBracket> brackets) {
		double totalTax = 0;
		double previousBracketUpper = 0;

		for (IncomeTaxBracket bracket : brackets) {
			if (taxableIncome <= previousBracketUpper) break;

			double upperBound = (bracket.getBracket() != null) ? bracket.getBracket() : taxableIncome;
			double taxableAmount  = Math.min(taxableIncome, upperBound) - previousBracketUpper;

			totalTax += taxableAmount * bracket.getRate();
			previousBracketUpper = upperBound;
		}

		return totalTax;
	}
}
