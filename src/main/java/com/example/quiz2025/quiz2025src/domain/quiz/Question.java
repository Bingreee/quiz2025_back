package com.example.quiz2025.quiz2025src.domain.quiz;

import com.example.quiz2025.quiz2025src.domain.category.Category;
import jakarta.persistence.*;
import com.example.quiz2025.quiz2025src.common.BaseEntity;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "question")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id; // 질문 ID

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
