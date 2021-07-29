package com.baghel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.apache.log4j.Logger;

import com.baghel.controller.CovidVaccineNotifierController;

@SpringBootApplication
@ComponentScan(basePackages = "com.baghel")
public class CovidVaccineNotifierApplication {

	private static Logger logger = Logger.getLogger(CovidVaccineNotifierApplication.class);

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(CovidVaccineNotifierApplication.class, args);
		logger.info("starting the application");
		CovidVaccineNotifierController covidNotifiercontroller = context.getBean(CovidVaccineNotifierController.class);
		logger.info("invoking of checkSlotAvailable");
		covidNotifiercontroller.checkSlotAvailable();
	}
}
