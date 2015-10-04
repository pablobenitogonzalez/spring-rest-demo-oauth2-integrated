package org.demo.service;

import org.demo.domain.Category;

import java.util.List;

@SuppressWarnings("unused")
public interface CategoryService {
    Category getCategory(Long id);
    List<Category> getAllCategories();
    Category createCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Long id);
}
