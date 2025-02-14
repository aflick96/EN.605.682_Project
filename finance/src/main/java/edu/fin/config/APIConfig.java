package edu.fin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class APIConfig {
	
	@Value("${server.api.base-url}")
	private String base;
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// User urls
	// ###################################################
	public String userRegisterUrl() {
		return base + "/users/register";
	}
	
	public String userLoginUrl() {
		return base + "/users/login";
	}
	// ###################################################

	// Income logs urls
	// ###################################################
	public String getIncomeLogsUrl(Long userId) {
		return base + "/income-logs/user/" + userId;
	}

	public String createIncomeLogUrl() {
		return base + "/income-logs";
	}

	public String deleteIncomeLogUrl(Long incomeLogId) {
		return base + "/income-logs/" + incomeLogId;
	}

	public String getIncomeLogDetails(Long incomeLogId) {
		return base + "/income-logs/" + incomeLogId + "/details";
	}
	// ###################################################
}
