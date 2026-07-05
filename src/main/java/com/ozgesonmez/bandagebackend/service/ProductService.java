package com.ozgesonmez.bandagebackend.service;

import com.ozgesonmez.bandagebackend.entity.Product;
import com.ozgesonmez.bandagebackend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import com.ozgesonmez.bandagebackend.exception.ProductNotFoundException;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAll(String filter, String sort, Long categoryId) {
        List<Product> products = productRepository.findAll();

        if (categoryId != null) {
            products = products.stream()
                    .filter(product ->
                            product.getCategory() != null &&
                                    product.getCategory().getId().equals(categoryId)
                    )
                    .toList();
        }

        if (filter != null && !filter.isBlank()) {
            String searchText = filter.toLowerCase();

            products = products.stream()
                    .filter(product ->
                            product.getName().toLowerCase().contains(searchText)
                                    || product.getDescription().toLowerCase().contains(searchText)
                                    || product.getBrand().toLowerCase().contains(searchText)
                                    || product.getCategory().getTitle().toLowerCase().contains(searchText)
                    )
                    .toList();
        }

        if (sort != null && !sort.isBlank()) {
            products = switch (sort) {
                case "price:asc" -> products.stream()
                        .sorted(Comparator.comparing(Product::getPrice))
                        .toList();

                case "price:desc" -> products.stream()
                        .sorted(Comparator.comparing(Product::getPrice).reversed())
                        .toList();

                case "rating:asc" -> products.stream()
                        .sorted(Comparator.comparing(Product::getRating))
                        .toList();

                case "rating:desc" -> products.stream()
                        .sorted(Comparator.comparing(Product::getRating).reversed())
                        .toList();

                default -> products;
            };
        }

        return products;
    }

    public Product findById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}