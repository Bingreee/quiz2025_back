package com.example.quiz2025.quiz2025src.domain.quiz;

import com.example.quiz2025.quiz2025src.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "question_detail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_detail_id")
    private Long id; // 질문상세 ID

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    private String questionContent; // 질문 내용

    private String questionAnswer; // 정답 내용

    private Integer questionType; // 0: 객관식, 1: 주관식

    private Integer questionLevel; //난이도

    @Builder
    public QuestionDetail(Long id, Question question, String questionContent, String questionAnswer, Integer questionType, Integer questionLevel) {
        this.id = id;
        this.question = question;
        this.questionContent = questionContent;
        this.questionAnswer = questionAnswer;
        this.questionType = questionType;
        this.questionLevel = questionLevel;
    }
}
