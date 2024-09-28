package com.example.inventory_management.controller;

import com.example.inventory_management.model.Category;
import com.example.inventory_management.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create a new Category
    @PostMapping
    public void createCategory(@RequestBody @Valid Category category) {
        categoryService.saveCategory(category);
    }

    // Get all Category
    @GetMapping
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    // Get total number of products
    @GetMapping("/count")
    public long getTotalCategory() {
        return categoryService.getTotalCat();
    }

    // Get a single Category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update an existing Category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateProduct(@PathVariable Long id, @Valid @RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.updateCategory(id, category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategoryById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
