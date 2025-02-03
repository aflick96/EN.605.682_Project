package edu.fin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class APIConfig {
	
	@Value("${server.api.base-url}")
	private String base;
	
	public String userRegisterUrl() {
		return base + "/users/register";
	}
	
	public String userLoginUrl() {
		return base + "/users/login";
	}
}
