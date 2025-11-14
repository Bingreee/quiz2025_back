package com.example.quiz2025.quiz2025src.repository.category;

import com.example.quiz2025.quiz2025src.dto.category.CategoryDto;

import java.util.List;

public interface CategoryRepositoryCustom {
    //카테고리 전체조회
    List<CategoryDto> searchCategories();
}
