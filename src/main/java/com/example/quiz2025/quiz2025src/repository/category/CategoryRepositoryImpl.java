package com.example.quiz2025.quiz2025src.repository.category;

import com.example.quiz2025.quiz2025src.dto.category.CategoryDto;
import com.example.quiz2025.quiz2025src.dto.category.QCategoryDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.quiz2025.quiz2025src.domain.category.QCategory.category;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CategoryDto> searchCategories() {
        return queryFactory
                .select(new QCategoryDto(
                        category.id,
                        category.categoryName
                ))
                .from(category)
                .fetch();
    }

}
