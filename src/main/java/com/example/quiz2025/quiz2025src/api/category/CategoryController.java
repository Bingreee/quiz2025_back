package com.example.quiz2025.quiz2025src.api.category;

import com.example.quiz2025.quiz2025src.common.Result;
import com.example.quiz2025.quiz2025src.common.ResultCode;
import com.example.quiz2025.quiz2025src.dto.category.CreateCategoryForm;
import com.example.quiz2025.quiz2025src.service.category.CreateCategoryService;
import com.example.quiz2025.quiz2025src.service.category.SearchCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {
    private final CreateCategoryService createCategoryService;
    private final SearchCategoryService searchCategoryService;

    /*카테고리 생성*/
    @PostMapping("/category")
    public Result createCategory(@RequestBody @Validated CreateCategoryForm createCategoryForm) {
        return new Result<>(ResultCode.SUCCESS, createCategoryService.createCategory(createCategoryForm), "result_success_save", "200");
    }

    /*카테고리 전체조회*/
    @GetMapping("/categories")
    public Result searchCategories() {
        return new Result<>(ResultCode.SUCCESS, searchCategoryService.searchCategories(), "result_success_search", "200");
    }
}
