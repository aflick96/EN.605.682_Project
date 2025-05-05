/*
 * APIConfig.java
 * 
 * This class is responsible for loading the API configuration properties from the application properties file.
 */

package edu.fin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class APIConfig {
	
	// Base URL for the API
	@Value("${server.api.base-url}")
	private String base;
	
	// Getters and Setters
	public String getBaseUrl() { return base; }
}
