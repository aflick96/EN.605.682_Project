package edu.fin.services;

import edu.fin.models.tax.FederalTax;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;

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
}
