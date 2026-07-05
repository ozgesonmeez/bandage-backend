package com.ozgesonmez.bandagebackend.controller;

import com.ozgesonmez.bandagebackend.dto.ProductResponse;
import com.ozgesonmez.bandagebackend.entity.Product;
import com.ozgesonmez.bandagebackend.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ProductResponse getProducts(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset
    ) {
        List<Product> products = productService.findAll();

        int safeLimit = limit == null ? products.size() : limit;
        int safeOffset = offset == null ? 0 : offset;

        List<Product> paginatedProducts = products
                .stream()
                .skip(safeOffset)
                .limit(safeLimit)
                .toList();

        return new ProductResponse(
                paginatedProducts,
                products.size(),
                safeLimit,
                safeOffset
        );
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }
}