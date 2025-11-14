package com.example.quiz2025;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Quiz2025Application {

	public static void main(String[] args) {
		SpringApplication.run(Quiz2025Application.class, args);
	}

}
