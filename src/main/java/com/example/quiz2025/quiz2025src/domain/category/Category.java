package com.example.quiz2025.quiz2025src.domain.category;

import com.example.quiz2025.quiz2025src.common.BaseEntity;
import com.example.quiz2025.quiz2025src.common.YesOrNo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String categoryName;

    @Enumerated(EnumType.STRING)
    private YesOrNo useYn;

    @Builder
    public Category(Long id, String categoryName, YesOrNo useYn) {
        this.id = id;
        this.categoryName = categoryName;
        this.useYn = useYn;
    }
}
