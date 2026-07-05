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

        Category tshirt = new Category(
                1L,
                "T-Shirt",
                "k",
                "t-shirt",
                4.7
        );

        Category jeans = new Category(
                2L,
                "Jeans",
                "e",
                "jeans",
                4.5
        );

        Category dress = new Category(
                3L,
                "Dress",
                "k",
                "dress",
                4.8
        );

        products.add(new Product(
                1L,
                "Classic T-Shirt",
                "Comfortable cotton t-shirt.",
                49.99,
                25,
                4.6,
                List.of(new ProductImage("https://picsum.photos/300/400?random=1")),
                "k",
                tshirt,
                "Bandage"
        ));

        products.add(new Product(
                2L,
                "Blue Jeans",
                "Slim fit blue jeans.",
                89.99,
                15,
                4.4,
                List.of(new ProductImage("https://picsum.photos/300/400?random=2")),
                "e",
                jeans,
                "Bandage"
        ));

        products.add(new Product(
                3L,
                "Summer Dress",
                "Light summer dress.",
                129.99,
                8,
                4.8,
                List.of(new ProductImage("https://picsum.photos/300/400?random=3")),
                "k",
                dress,
                "Bandage"
        ));
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