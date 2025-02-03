package edu.fin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FinanceWebApp extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(FinanceWebApp.class, args);
	}
}