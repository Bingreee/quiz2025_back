package com.example.quiz2025.quiz2025src.repository.question;

import com.example.quiz2025.quiz2025src.domain.quiz.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
