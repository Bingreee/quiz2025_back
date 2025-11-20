package com.example.quiz2025.quiz2025src.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConfigurationProperties(prefix = "openai")
@Getter
@Setter
public class GptConfig {

    @Value("${openai.api.key}")
    private String apiKey;
    @Value("${openai.model}")
    private String model;
    @Value("${openai.url}")
    private String url;

    @PostConstruct
    public void check() {
        //System.out.println("### QUIZ2025_USERNAME = " + apiKey);
    }

    @Bean
    public WebClient openAiWebClient() {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Getter
    private String prompt = """
        You are a quiz generator.  
        Generate exactly 25 quiz items for the category: "%s".  
        Create a mixture of multiple-choice (type = 0) and short-answer (type = 1) questions.
        Double-check the question, the correct answer, and the choices you created.
        
        Return the result ONLY in the following JSON format.  
        No explanations, no text outside JSON.  
        Strictly output valid JSON.  
        Values must match the type exactly.
        
        {
        "items": [
        {
        "questionContent": "string",
        "questionAnswer": "string",
        "questionType": 0 or 1,
        "questionLevel": 1 or 2 or 3,
        "choices": [
        {
          "choiceNumber": 1,
          "choiceContent": "string",
          "answerYn": true or false
        }
        ]
        }
        ]
        }
        
        Rules:
        1. For multiple-choice questions (questionType = 0):
        - Provide 3 to 5 choices.
        - Exactly one choice must have "answerYn": true.
        - Fill both questionAnswer and choices.
        - questionAnswer must match the correct choiceContent.
        
        2. For short-answer questions (questionType = 1):
        - Do NOT include the "choices" field.
        - Always fill questionAnswer.
        
        3. "questionLevel" must be an integer:  
        1 = easy, 2 = medium, 3 = hard.
        
        4. Output valid JSON only.  
        No Markdown, no code block, no natural language.
        
        Start now.
        """;

}
