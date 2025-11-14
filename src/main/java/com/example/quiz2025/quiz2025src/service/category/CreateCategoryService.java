package com.example.quiz2025.quiz2025src.service.category;

import com.example.quiz2025.quiz2025src.common.YesOrNo;
import com.example.quiz2025.quiz2025src.domain.category.Category;
import com.example.quiz2025.quiz2025src.dto.category.CategoryDto;
import com.example.quiz2025.quiz2025src.dto.category.CreateCategoryForm;
import com.example.quiz2025.quiz2025src.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreateCategoryService {

    private final CategoryRepository categoryRepository;

    /*카테고리 생성*/
    public Long createCategory(CreateCategoryForm form) {
        Category category = categoryRepository.save(
          Category.builder()
                  .categoryName(form.getCategoryName())
                  .useYn(YesOrNo.valueOf(form.getUseYn()))
                  .build()
        );
        return category.getId();
    }
}
