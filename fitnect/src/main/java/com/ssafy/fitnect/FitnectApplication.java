package com.ssafy.fitnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;

import com.ssafy.fitnect.model.dto.Gym;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@Import(RestExceptionHandler.class)
public class FitnectApplication {
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(FitnectApplication.class, args);
	}
	
}
