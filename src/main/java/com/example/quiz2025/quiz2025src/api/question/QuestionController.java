package com.example.quiz2025.quiz2025src.api.question;

import com.example.quiz2025.quiz2025src.common.Result;
import com.example.quiz2025.quiz2025src.common.ResultCode;
import com.example.quiz2025.quiz2025src.dto.question.ChatGptRequestDto;
import com.example.quiz2025.quiz2025src.dto.question.QuestionDetailDto;
import com.example.quiz2025.quiz2025src.service.category.CreateQuestionService;
import com.example.quiz2025.quiz2025src.service.question.SearchQuestionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuestionController {
    private final CreateQuestionService createQuestionService;
    private final SearchQuestionsService searchQuestionsService;

    /* 질문 생성(+저장까지)*/
    @PostMapping("/questions")
    public Result createQuestions(@RequestBody ChatGptRequestDto dto) {
        //QuizResponseDto question = chatGptService.getQuestions(categoryId);
        return new Result<>(ResultCode.SUCCESS, createQuestionService.generateAndSaveQuestions(dto.getCategoryId()), "result_success_save", "200");
    }

    /* 질문 조회 */
    @GetMapping("/questions/{questionId}")
    public List<QuestionDetailDto> searchQuestions(@PathVariable Long questionId) {
        return searchQuestionsService.searchQuestions(questionId);
    }
}
