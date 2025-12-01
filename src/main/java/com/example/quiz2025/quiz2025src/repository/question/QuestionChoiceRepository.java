package com.example.quiz2025.quiz2025src.repository.question;

import com.example.quiz2025.quiz2025src.domain.quiz.QuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionChoiceRepository extends JpaRepository<QuestionChoice, Long> {
    List<QuestionChoice> findByQuestionDetailId(Long questionDetailId);
}
