package com.example.quiz2025.quiz2025src.service.category;

import com.example.quiz2025.quiz2025src.dto.category.CategoryDto;
import com.example.quiz2025.quiz2025src.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchCategoryService {

    private final CategoryRepository categoryRepository;

    /*카테고리 전체 조회*/
    @Transactional
    public List<CategoryDto> searchCategories() {
        List<CategoryDto> dtos = categoryRepository.searchCategories();
        return dtos;
    }
}
