package com.ozgesonmez.bandagebackend.repository;

import com.ozgesonmez.bandagebackend.entity.Category;
import com.ozgesonmez.bandagebackend.entity.Product;
import com.ozgesonmez.bandagebackend.entity.ProductImage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public ProductRepository() {
        Category tshirt = new Category(1L, "T-Shirt", "k", "t-shirt", 4.7);
        Category dress = new Category(2L, "Dress", "k", "dress", 4.8);
        Category jeans = new Category(3L, "Jeans", "e", "jeans", 4.5);
        Category jacket = new Category(4L, "Jacket", "e", "jacket", 4.6);
        Category shoes = new Category(5L, "Shoes", "e", "shoes", 4.4);

        products.add(new Product(1L, "Classic T-Shirt", "Comfortable cotton t-shirt.", 49.99, 25, 4.6, List.of(new ProductImage("https://picsum.photos/300/400?random=1")), "k", tshirt, "Bandage"));
        products.add(new Product(2L, "Oversized T-Shirt", "Relaxed fit oversized t-shirt.", 59.99, 18, 4.7, List.of(new ProductImage("https://picsum.photos/300/400?random=2")), "k", tshirt, "Bandage"));
        products.add(new Product(3L, "Summer Dress", "Light summer dress.", 129.99, 8, 4.8, List.of(new ProductImage("https://picsum.photos/300/400?random=3")), "k", dress, "Bandage"));
        products.add(new Product(4L, "Floral Dress", "Elegant floral dress for summer.", 149.99, 10, 4.9, List.of(new ProductImage("https://picsum.photos/300/400?random=4")), "k", dress, "Bandage"));

        products.add(new Product(5L, "Blue Jeans", "Slim fit blue jeans.", 89.99, 15, 4.4, List.of(new ProductImage("https://picsum.photos/300/400?random=5")), "e", jeans, "Bandage"));
        products.add(new Product(6L, "Black Jeans", "Modern black denim jeans.", 99.99, 12, 4.5, List.of(new ProductImage("https://picsum.photos/300/400?random=6")), "e", jeans, "Bandage"));
        products.add(new Product(7L, "Leather Jacket", "Classic leather jacket.", 219.99, 6, 4.8, List.of(new ProductImage("https://picsum.photos/300/400?random=7")), "e", jacket, "Bandage"));
        products.add(new Product(8L, "Denim Jacket", "Casual denim jacket.", 179.99, 9, 4.6, List.of(new ProductImage("https://picsum.photos/300/400?random=8")), "e", jacket, "Bandage"));

        products.add(new Product(9L, "White Sneakers", "Everyday white sneakers.", 119.99, 20, 4.5, List.of(new ProductImage("https://picsum.photos/300/400?random=9")), "e", shoes, "Bandage"));
        products.add(new Product(10L, "Running Shoes", "Lightweight running shoes.", 139.99, 14, 4.7, List.of(new ProductImage("https://picsum.photos/300/400?random=10")), "e", shoes, "Bandage"));
        products.add(new Product(11L, "Basic Hoodie", "Soft fleece hoodie.", 109.99, 16, 4.6, List.of(new ProductImage("https://picsum.photos/300/400?random=11")), "k", tshirt, "Bandage"));
        products.add(new Product(12L, "Premium Coat", "Warm premium winter coat.", 249.99, 5, 4.9, List.of(new ProductImage("https://picsum.photos/300/400?random=12")), "e", jacket, "Bandage"));
    }

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }
}