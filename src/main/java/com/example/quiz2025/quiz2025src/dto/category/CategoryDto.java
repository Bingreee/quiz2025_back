package com.example.quiz2025.quiz2025src.dto.category;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class CategoryDto {
    private Long categoryId;
    private String categoryName;

    @QueryProjection
    public CategoryDto(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
