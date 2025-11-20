package com.example.quiz2025.quiz2025src.service.question;

import com.example.quiz2025.quiz2025src.config.GptConfig;
import com.example.quiz2025.quiz2025src.domain.category.Category;
import com.example.quiz2025.quiz2025src.dto.question.ChatGptRequest;
import com.example.quiz2025.quiz2025src.dto.question.ChatGptMessage;
import com.example.quiz2025.quiz2025src.dto.question.ChatGptResponse;
import com.example.quiz2025.quiz2025src.dto.question.QuizResponseDto;
import com.example.quiz2025.quiz2025src.repository.category.CategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatGptService {

    private final WebClient openAiWebClient;
    private final GptConfig gptConfig;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CategoryRepository categoryRepository;

    public QuizResponseDto getQuestions(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);

        String prompt = String.format(gptConfig.getPrompt(), category.getCategoryName());

        gptConfig.check();
        // 1. ChatGPT 요청 Body 구성
        ChatGptRequest request = new ChatGptRequest(
                gptConfig.getModel(),
                List.of(new ChatGptMessage("user", prompt))
        );

        // 2. ChatGPT API 호출 → ChatGptResponse로 받기
        ChatGptResponse response = openAiWebClient.post()
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatGptResponse.class)
                .block();

        if (response == null || response.getChoices() == null) {
            throw new IllegalStateException("OpenAI 응답 오류");
        }

        // 3. ChatGPT가 생성한 JSON 문자열 꺼내기
        String json = response
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();

        log.info("ChatGPT raw json: {}", json);

        // 4. content 문자열(JSON)을 QuizResponseDto로 변환
        try {
            return objectMapper.readValue(json, QuizResponseDto.class);
        } catch (JsonProcessingException e) {
            log.error("JSON 파싱 오류: {}", e.getMessage());
            throw new IllegalStateException("QuizResponseDto 변환 실패: JSON 형식이 잘못됨", e);
        }
    }
}


