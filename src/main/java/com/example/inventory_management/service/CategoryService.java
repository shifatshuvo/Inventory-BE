package com.example.inventory_management.service;

import com.example.inventory_management.model.Category;
import com.example.inventory_management.repository.CategoryRepository;
import com.example.inventory_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    // Save Product
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    // Update Category
    public Category updateCategory(Long id, Category updatedCategory) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            category.setName(updatedCategory.getName());
            category.setImgUrl(updatedCategory.getImgUrl());

            return categoryRepository.save(category);
        } else {
            throw new IllegalArgumentException("Category not found");
        }
    }

    // Get all Category
    @Transactional(readOnly = true)
    public List<Category> getAllCategory() {
        List<Category> all = categoryRepository.findAll();
        return all;
    }

    // Get a single Category by ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Get total cat num
    public long getTotalCat() {
        return categoryRepository.count();
    }

    // Delete a Category
    @Transactional
    public void deleteCategoryById(Long id) {
        // Check if the category exists
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category not found");
        }

        // Step 1: Delete all products associated with the category
        productRepository.deleteByCategoryId(id);

        // Step 2: Delete the category
        categoryRepository.deleteById(id);
    }
}
