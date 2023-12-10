package com.in28minutes.jpa.inheritance;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.inheritance.entity.FullTimeEmployee;
import com.in28minutes.jpa.inheritance.entity.PartTimeEmployee;
import com.in28minutes.jpa.inheritance.repository.EmployeeRepository;

@SpringBootApplication
public class JpaInheritanceApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaInheritanceApplication.class, args);
		
	}
	
	public void run(String... args) throws Exception {
		// jack is fulltimeeployee - 10000$ salary
		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));
		// jill is parttimeemployee - 50$ per hour
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		
		// logger.info("All employees -> {}", employeeRepository.retrieveAllEmployees());
		logger.info("All employees -> {}", employeeRepository.retrieveAllFullTimeEmployees());
		logger.info("All employees -> {}", employeeRepository.retrieveAllPartTimeEmployees());
	}

}
