package edu.fin.services;

import edu.fin.models.tax.FederalTax;
import edu.fin.models.tax.FederalTax.IncomeTaxBracket;
import edu.fin.models.tax.FederalTax.FilingStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FederalTaxService {
	private final ObjectMapper objectMapper;
	
	public FederalTaxService() {
		this.objectMapper = new ObjectMapper();
		this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
	}
	
	public FederalTax getFederalTaxes() throws IOException {
		ClassPathResource resource = new ClassPathResource("federal-taxes.json");
		try (InputStream input = resource.getInputStream()) {
			return objectMapper.readValue(input, FederalTax.class);
		}		
	}

	public double calculateFederalTax(double grossIncome, String filingStatus) {
		try {
			FederalTax taxData = getFederalTaxes();
			FilingStatus taxBrackets = filingStatus.equalsIgnoreCase("married") ? taxData.getMarried() : taxData.getSingle();
			double taxableIncome = Math.max(0, grossIncome - taxBrackets.getStandardDeduction());
			return computeProgressiveTax(taxableIncome, taxBrackets.getIncomeTaxBrackets());
		} catch (IOException e) {
			System.err.println("Error reading federal tax data: " + e.getMessage());
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
