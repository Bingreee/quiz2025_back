package com.example.quiz2025;

import com.example.quiz2025.quiz2025src.config.GptConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(GptConfig.class)
public class Quiz2025Application {

	public static void main(String[] args) {
		SpringApplication.run(Quiz2025Application.class, args);
	}

}
