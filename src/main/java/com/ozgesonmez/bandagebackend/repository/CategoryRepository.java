package com.ozgesonmez.bandagebackend.repository;

import com.ozgesonmez.bandagebackend.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {

    private final List<Category> categories = new ArrayList<>();

    public CategoryRepository() {
        categories.add(new Category(1L, "T-Shirt", "k", "t-shirt", 4.7));
        categories.add(new Category(2L, "Dress", "k", "dress", 4.8));
        categories.add(new Category(3L, "Jeans", "e", "jeans", 4.5));
        categories.add(new Category(4L, "Jacket", "e", "jacket", 4.6));
    }

    public List<Category> findAll() {
        return categories;
    }

    public Optional<Category> findById(Long id) {
        return categories
                .stream()
                .filter(category -> category.getId().equals(id))
                .findFirst();
    }
}