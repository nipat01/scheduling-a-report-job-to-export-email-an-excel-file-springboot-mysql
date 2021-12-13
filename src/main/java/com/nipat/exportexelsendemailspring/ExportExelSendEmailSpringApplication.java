package com.nipat.exportexelsendemailspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExportExelSendEmailSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExportExelSendEmailSpringApplication.class, args);
	}

}
