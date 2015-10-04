package org.demo.service;

import org.demo.domain.Subcategory;

import java.util.List;

@SuppressWarnings("unused")
public interface SubcategoryService {
    Subcategory getSubcategory(Long id);
    List<Subcategory> getAllSubcategories();
    Subcategory createSubcategory(Subcategory subcategory);
    void updateSubcategory(Subcategory subcategory);
    void deleteSubcategory(Long id);
}
