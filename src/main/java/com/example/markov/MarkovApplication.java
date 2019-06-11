package com.example.markov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MarkovApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkovApplication.class, args);
	}

}
