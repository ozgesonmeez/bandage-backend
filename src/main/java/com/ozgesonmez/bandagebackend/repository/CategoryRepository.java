package com.ozgesonmez.bandagebackend.repository;

import com.ozgesonmez.bandagebackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}