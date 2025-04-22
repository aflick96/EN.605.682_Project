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

	public String getBaseUrl() {
		return base;
	}

	/* User */ 
	// ###################################################
	public String userRegisterUrl() {
		return base + "/users/register";
	}
	
	public String userLoginUrl() {
		return base + "/users/login";
	}
	// ###################################################

	/* Income logs */
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

	/* Expense logs */
	// ###################################################
	public String getExpenseLogsUrl(Long userId) {
		return base + "/expenses/user/" + userId;
	}

	public String createExpenseLogItemUrl(Long userId) {
		return base + "/expenses/user/" + userId + "/items";
	}

	public String deleteExpenseLogItemUrl(Long expenseItemId) {
		return base + "/expenses/items/" + expenseItemId;
	}
}
