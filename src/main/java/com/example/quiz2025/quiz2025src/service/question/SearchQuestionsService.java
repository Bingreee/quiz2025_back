package com.example.quiz2025.quiz2025src.service.question;

import com.example.quiz2025.quiz2025src.domain.quiz.QuestionChoice;
import com.example.quiz2025.quiz2025src.domain.quiz.QuestionDetail;
import com.example.quiz2025.quiz2025src.dto.category.CategoryDto;
import com.example.quiz2025.quiz2025src.dto.question.QuestionChoiceDto;
import com.example.quiz2025.quiz2025src.dto.question.QuestionDetailDto;
import com.example.quiz2025.quiz2025src.repository.category.CategoryRepository;
import com.example.quiz2025.quiz2025src.repository.question.QuestionChoiceRepository;
import com.example.quiz2025.quiz2025src.repository.question.QuestionDetailRepository;
import com.example.quiz2025.quiz2025src.repository.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchQuestionsService {

    private final QuestionDetailRepository questionDetailRepository;
    private final QuestionChoiceRepository questionChoiceRepository;

    /*질문 25개 조회*/
    @Transactional
    public List<QuestionDetailDto> searchQuestions(Long questionId) {
        // 1. questionId 로 모든 질문 상세 가져오기
        List<QuestionDetail> details =
                questionDetailRepository.findByQuestionId(questionId);

        List<QuestionDetailDto> result = new ArrayList<>();

        for (QuestionDetail detail : details) {

            // DTO 매핑
            QuestionDetailDto dto = new QuestionDetailDto();
            dto.setQuestionId(detail.getQuestion().getId().intValue());
            dto.setQuestionDetailId(detail.getId().intValue());
            dto.setQuestionContent(detail.getQuestionContent());
            dto.setQuestionAnswer(detail.getQuestionAnswer());
            dto.setQuestionType(detail.getQuestionType());

            // 객관식인 경우만 choice 조회
            if (detail.getQuestionType() == 0) {
                List<QuestionChoice> choices =
                        questionChoiceRepository.findByQuestionDetailId(detail.getId());

                List<QuestionChoiceDto> choiceDtos = new ArrayList<>();

                for (QuestionChoice choice : choices) {
                    QuestionChoiceDto cDto = new QuestionChoiceDto();
                    cDto.setChoiceId(choice.getId().intValue());
                    cDto.setQuestionDetailId(choice.getQuestionDetail().getId().intValue());
                    cDto.setChoiceContent(choice.getChoiceContent());
                    cDto.setChoiceNumber(choice.getChoiceNumber());
                    cDto.setAnswerYn(String.valueOf(choice.getAnswerYn()));

                    choiceDtos.add(cDto);
                }

                dto.setChoices(choiceDtos);
            }

            result.add(dto);
        }

        return result;
    }
}
