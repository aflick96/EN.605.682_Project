package edu.fin.services;

import edu.fin.models.tax.StateTax;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;

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
}
