package com.example.quiz2025.quiz2025src.domain.quiz;

import com.example.quiz2025.quiz2025src.common.BaseEntity;
import com.example.quiz2025.quiz2025src.domain.base.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "answer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id; // 답변 ID

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id")
    private Question question; // 질문

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 사용자

    private String answerContent; // 답변 내용

    private Boolean answerYn; // 정답 여부
}
