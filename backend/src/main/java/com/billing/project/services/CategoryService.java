package com.billing.project.services;

import java.util.List;

import com.billing.project.dto.ApiResponse;
import com.billing.project.dto.CategoryRequest;
import com.billing.project.entities.Category;

public interface CategoryService {

    ApiResponse deleteCategory(Long id);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
	Category createCategory(CategoryRequest category);
	Category updateCategory(Long id, CategoryRequest categoryDetails);
}
