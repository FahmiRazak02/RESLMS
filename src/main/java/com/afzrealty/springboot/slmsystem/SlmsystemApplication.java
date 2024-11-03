package com.afzrealty.springboot.slmsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "com.afzrealty.springboot.slmsystem")
public class SlmsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlmsystemApplication.class, args);
	}

}
