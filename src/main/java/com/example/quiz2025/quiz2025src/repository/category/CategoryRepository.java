package com.example.quiz2025.quiz2025src.repository.category;

import com.example.quiz2025.quiz2025src.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom{
}
