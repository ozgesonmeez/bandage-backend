package com.ozgesonmez.bandagebackend.service;

import com.ozgesonmez.bandagebackend.entity.Category;
import com.ozgesonmez.bandagebackend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}