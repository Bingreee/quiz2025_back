package com.example.quiz2025.quiz2025src.repository.question;

import com.example.quiz2025.quiz2025src.domain.quiz.QuestionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionDetailRepository extends JpaRepository<QuestionDetail, Long> {
    List<QuestionDetail> findByQuestionId(Long questionId);

}
