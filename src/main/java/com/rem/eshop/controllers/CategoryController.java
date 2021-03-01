package com.rem.eshop.controllers;

import java.util.List;
import java.util.Optional;

import com.rem.eshop.models.Category;
import com.rem.eshop.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Category getCategoryByID(@PathVariable Long id) {
        Optional<Category> optCategory = categoryRepository.findById(id);
        if (optCategory.isPresent()) {
            return optCategory.get();
        } else {
            throw new RuntimeException("Category not found with id " + id);
            // throw new RuntimeException("Category not found with id " + id);
        }
    }

    @PostMapping("/categories")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/categories/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryUpdated) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(categoryUpdated.getName());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public StringResponseFormat deleteCategory(@PathVariable Long id) {
        return categoryRepository.findById(id).map(category -> {
            categoryRepository.delete(category);
            return new StringResponseFormat("Delete Successfully!");
        }).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }
}
