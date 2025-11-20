package com.example.quiz2025.quiz2025src.service.category;

import com.example.quiz2025.quiz2025src.common.YesOrNo;
import com.example.quiz2025.quiz2025src.domain.category.Category;
import com.example.quiz2025.quiz2025src.domain.quiz.Question;
import com.example.quiz2025.quiz2025src.domain.quiz.QuestionChoice;
import com.example.quiz2025.quiz2025src.domain.quiz.QuestionDetail;
import com.example.quiz2025.quiz2025src.dto.category.CreateCategoryForm;
import com.example.quiz2025.quiz2025src.dto.question.QuizResponseDto;
import com.example.quiz2025.quiz2025src.repository.category.CategoryRepository;
import com.example.quiz2025.quiz2025src.repository.question.QuestionChoiceRepository;
import com.example.quiz2025.quiz2025src.repository.question.QuestionDetailRepository;
import com.example.quiz2025.quiz2025src.repository.question.QuestionRepository;
import com.example.quiz2025.quiz2025src.service.question.ChatGptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreateQuestionService {

    private final ChatGptService chatGptService;
    private final QuestionRepository questionRepository;
    private final QuestionDetailRepository questionDetailRepository;
    private final QuestionChoiceRepository questionChoiceRepository;
    private final CategoryRepository categoryRepository;

    public Long generateAndSaveQuestions(Long categoryId) {

        // 1. category 조회
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        // 2. ChatGPT에서 문제 25개 가져옴
        QuizResponseDto quizDto = chatGptService.getQuestions(categoryId);

        // 3. 대표 Question 생성
        Question question = Question.builder()
                .category(category)
                .build();

        questionRepository.save(question);

        // 4. 하나씩 detail/choice 저장
        for (QuizResponseDto.QuizItem item : quizDto.getItems()) {

            // QuestionDetail 저장
            QuestionDetail detail = QuestionDetail.builder()
                    .question(question)
                    .questionContent(item.getQuestionContent())
                    .questionAnswer(item.getQuestionAnswer())
                    .questionType(item.getQuestionType())
                    .questionLevel(item.getQuestionLevel())
                    .build();

            questionDetailRepository.save(detail);

            // 객관식인 경우 choice 저장
            if (item.getQuestionType() == 0 && item.getChoices() != null) {
                for (QuizResponseDto.ChoiceItem c : item.getChoices()) {

                    QuestionChoice choice = QuestionChoice.builder()
                            .questionDetail(detail)
                            .choiceContent(c.getChoiceContent())
                            .choiceNumber(c.getChoiceNumber())
                            .answerYn(c.getAnswerYn())
                            .build();

                    questionChoiceRepository.save(choice);
                }
            }
        }

        return question.getId();
    }
}
