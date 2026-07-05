package com.ozgesonmez.bandagebackend.config;

import com.ozgesonmez.bandagebackend.entity.Category;
import com.ozgesonmez.bandagebackend.entity.Product;
import com.ozgesonmez.bandagebackend.entity.ProductImage;
import com.ozgesonmez.bandagebackend.repository.CategoryRepository;
import com.ozgesonmez.bandagebackend.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataInitializer(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        Category tshirt = categoryRepository.save(new Category(null, "T-Shirt", "k", "t-shirt", 4.7));
        Category dress = categoryRepository.save(new Category(null, "Dress", "k", "dress", 4.8));
        Category jeans = categoryRepository.save(new Category(null, "Jeans", "e", "jeans", 4.5));
        Category jacket = categoryRepository.save(new Category(null, "Jacket", "e", "jacket", 4.6));
        Category shoes = categoryRepository.save(new Category(null, "Shoes", "e", "shoes", 4.4));

        productRepository.save(new Product(null, "Classic T-Shirt", "Comfortable cotton t-shirt.", 49.99, 25, 4.6, List.of(new ProductImage("https://picsum.photos/300/400?random=1")), "k", tshirt, "Bandage"));
        productRepository.save(new Product(null, "Oversized T-Shirt", "Relaxed fit oversized t-shirt.", 59.99, 18, 4.7, List.of(new ProductImage("https://picsum.photos/300/400?random=2")), "k", tshirt, "Bandage"));
        productRepository.save(new Product(null, "Summer Dress", "Light summer dress.", 129.99, 8, 4.8, List.of(new ProductImage("https://picsum.photos/300/400?random=3")), "k", dress, "Bandage"));
        productRepository.save(new Product(null, "Floral Dress", "Elegant floral dress for summer.", 149.99, 10, 4.9, List.of(new ProductImage("https://picsum.photos/300/400?random=4")), "k", dress, "Bandage"));

        productRepository.save(new Product(null, "Blue Jeans", "Slim fit blue jeans.", 89.99, 15, 4.4, List.of(new ProductImage("https://picsum.photos/300/400?random=5")), "e", jeans, "Bandage"));
        productRepository.save(new Product(null, "Black Jeans", "Modern black denim jeans.", 99.99, 12, 4.5, List.of(new ProductImage("https://picsum.photos/300/400?random=6")), "e", jeans, "Bandage"));
        productRepository.save(new Product(null, "Leather Jacket", "Classic leather jacket.", 219.99, 6, 4.8, List.of(new ProductImage("https://picsum.photos/300/400?random=7")), "e", jacket, "Bandage"));
        productRepository.save(new Product(null, "Denim Jacket", "Casual denim jacket.", 179.99, 9, 4.6, List.of(new ProductImage("https://picsum.photos/300/400?random=8")), "e", jacket, "Bandage"));

        productRepository.save(new Product(null, "White Sneakers", "Everyday white sneakers.", 119.99, 20, 4.5, List.of(new ProductImage("https://picsum.photos/300/400?random=9")), "e", shoes, "Bandage"));
        productRepository.save(new Product(null, "Running Shoes", "Lightweight running shoes.", 139.99, 14, 4.7, List.of(new ProductImage("https://picsum.photos/300/400?random=10")), "e", shoes, "Bandage"));
        productRepository.save(new Product(null, "Basic Hoodie", "Soft fleece hoodie.", 109.99, 16, 4.6, List.of(new ProductImage("https://picsum.photos/300/400?random=11")), "k", tshirt, "Bandage"));
        productRepository.save(new Product(null, "Premium Coat", "Warm premium winter coat.", 249.99, 5, 4.9, List.of(new ProductImage("https://picsum.photos/300/400?random=12")), "e", jacket, "Bandage"));
    }
}