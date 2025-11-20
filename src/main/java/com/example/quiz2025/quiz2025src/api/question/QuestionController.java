package com.example.quiz2025.quiz2025src.api.question;

import com.example.quiz2025.quiz2025src.common.Result;
import com.example.quiz2025.quiz2025src.common.ResultCode;
import com.example.quiz2025.quiz2025src.dto.question.ChatGptRequestDto;
import com.example.quiz2025.quiz2025src.service.category.CreateQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuestionController {
    private final CreateQuestionService createQuestionService;

    /*질문 생성*/
    @PostMapping("/questions")
    public Result createQuestions(@RequestBody ChatGptRequestDto dto) {
        //QuizResponseDto question = chatGptService.getQuestions(categoryId);
        return new Result<>(ResultCode.SUCCESS, createQuestionService.generateAndSaveQuestions(dto.getCategoryId()), "result_success_save", "200");
    }
}
