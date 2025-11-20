package com.example.quiz2025.quiz2025src.domain.quiz;

import com.example.quiz2025.quiz2025src.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "question_choice")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionChoice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_choice_id")
    private Long id; // 질문상세 ID

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_detail_id")
    private QuestionDetail questionDetail;

    private String choiceContent; // 선택지 내용

    private Integer choiceNumber; // 선택 번호

    private Boolean answerYn; // 정답 여부

    @Builder
    public QuestionChoice(Long id, QuestionDetail questionDetail, String choiceContent, Integer choiceNumber, Boolean answerYn) {
        this.id = id;
        this.questionDetail = questionDetail;
        this.choiceContent = choiceContent;
        this.choiceNumber = choiceNumber;
        this.answerYn = answerYn;
    }
}
