package com.billing.project.services;

import java.util.List;

import com.billing.project.entities.Category;

public interface CategoryService {
	Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
}
