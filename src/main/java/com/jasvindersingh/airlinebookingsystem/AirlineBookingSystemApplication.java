package com.jasvindersingh.airlinebookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class AirlineBookingSystemApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(AirlineBookingSystemApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(AirlineBookingSystemApplication.class, args);
		logger.info("AirlineBookingSystemApplication Started");
	}

}
