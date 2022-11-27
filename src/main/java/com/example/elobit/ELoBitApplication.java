package com.example.elobit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ELoBitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELoBitApplication.class, args);
	}


}
