package com.example.piapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiAppApplication.class, args);
	}

}
