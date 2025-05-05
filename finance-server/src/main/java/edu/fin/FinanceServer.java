/*
 * FinanceServer.java
 * 
 * This is the main entry point for the FinanceServer application. 
 */
package edu.fin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FinanceServer extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(FinanceServer.class, args);
    }
}