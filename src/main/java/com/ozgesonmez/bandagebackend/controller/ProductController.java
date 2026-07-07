package com.ozgesonmez.bandagebackend.controller;

import com.ozgesonmez.bandagebackend.dto.response.ProductResponse;
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
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false, name = "category") Long categoryId
    ) {
        List<Product> filteredProducts = productService.findAll(filter, sort, categoryId);

        int safeOffset = offset == null ? 0 : offset;
        int safeLimit = limit == null ? filteredProducts.size() : limit;

        List<Product> paginatedProducts = filteredProducts
                .stream()
                .skip(safeOffset)
                .limit(safeLimit)
                .toList();

        return new ProductResponse(
                paginatedProducts,
                filteredProducts.size(),
                safeLimit,
                safeOffset
        );
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }
}