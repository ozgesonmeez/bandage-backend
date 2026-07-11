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

    public DataInitializer(
            CategoryRepository categoryRepository,
            ProductRepository productRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        Category womenTshirt = getOrCreateCategory(
                "Tişört",
                "k",
                "k:tisort",
                4.2,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_kadın_tişört.jpg"
        );

        Category womenShoes = getOrCreateCategory(
                "Ayakkabı",
                "k",
                "k:ayakkabi",
                4.9,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_kadın_ayakkabı.jpg"
        );

        Category womenJacket = getOrCreateCategory(
                "Ceket",
                "k",
                "k:ceket",
                3.8,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_kadın_ceket.jpg"
        );

        Category womenDress = getOrCreateCategory(
                "Elbise",
                "k",
                "k:elbise",
                4.1,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_kadın_elbise.jpg"
        );

        Category womenSkirt = getOrCreateCategory(
                "Etek",
                "k",
                "k:etek",
                3.9,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_kadın_etek.jpg"
        );

        Category womenShirt = getOrCreateCategory(
                "Gömlek",
                "k",
                "k:gomlek",
                3.1,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_kadın_gömlek.jpg"
        );

        Category womenSweater = getOrCreateCategory(
                "Kazak",
                "k",
                "k:kazak",
                2.9,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_kadın_kazak.jpg"
        );

        Category womenTrousers = getOrCreateCategory(
                "Pantalon",
                "k",
                "k:pantalon",
                3.8,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_kadın_pantalon.jpg"
        );

        Category menShoes = getOrCreateCategory(
                "Ayakkabı",
                "e",
                "e:ayakkabi",
                4.6,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_erkek_ayakkabı.jpg"
        );

        Category menJacket = getOrCreateCategory(
                "Ceket",
                "e",
                "e:ceket",
                4.1,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_erkek_ceket.jpg"
        );

        Category menShirt = getOrCreateCategory(
                "Gömlek",
                "e",
                "e:gomlek",
                3.9,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_erkek_gömlek.jpg"
        );

        Category menSweater = getOrCreateCategory(
                "Kazak",
                "e",
                "e:kazak",
                3.2,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_erkek_kazak.jpg"
        );

        Category menTrousers = getOrCreateCategory(
                "Pantalon",
                "e",
                "e:pantalon",
                3.5,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_erkek_pantalon.jpg"
        );

        Category menTshirt = getOrCreateCategory(
                "Tişört",
                "e",
                "e:tisort",
                4.3,
                "https://workintech-fe-ecommerce.onrender.com/assets/category-img/category_erkek_tişört.jpg"
        );

        saveProductIfMissing(
                "Classic T-Shirt",
                "Comfortable cotton t-shirt.",
                49.99,
                25,
                4.6,
                "https://picsum.photos/300/400?random=1",
                "k",
                womenTshirt
        );

        saveProductIfMissing(
                "Oversized T-Shirt",
                "Relaxed fit oversized t-shirt.",
                59.99,
                18,
                4.7,
                "https://picsum.photos/300/400?random=2",
                "k",
                womenTshirt
        );

        saveProductIfMissing(
                "Women White Sneakers",
                "Comfortable everyday sneakers.",
                119.99,
                20,
                4.8,
                "https://picsum.photos/300/400?random=13",
                "k",
                womenShoes
        );

        saveProductIfMissing(
                "Women Running Shoes",
                "Lightweight running shoes for women.",
                139.99,
                14,
                4.7,
                "https://picsum.photos/300/400?random=14",
                "k",
                womenShoes
        );

        saveProductIfMissing(
                "Women Denim Jacket",
                "Modern denim jacket.",
                179.99,
                9,
                4.3,
                "https://picsum.photos/300/400?random=15",
                "k",
                womenJacket
        );

        saveProductIfMissing(
                "Women Leather Jacket",
                "Classic leather jacket for women.",
                219.99,
                6,
                4.6,
                "https://picsum.photos/300/400?random=16",
                "k",
                womenJacket
        );

        saveProductIfMissing(
                "Summer Dress",
                "Light summer dress.",
                129.99,
                8,
                4.8,
                "https://picsum.photos/300/400?random=3",
                "k",
                womenDress
        );

        saveProductIfMissing(
                "Floral Dress",
                "Elegant floral dress for summer.",
                149.99,
                10,
                4.9,
                "https://picsum.photos/300/400?random=4",
                "k",
                womenDress
        );

        saveProductIfMissing(
                "Pleated Skirt",
                "Elegant pleated skirt.",
                79.99,
                17,
                4.2,
                "https://picsum.photos/300/400?random=17",
                "k",
                womenSkirt
        );

        saveProductIfMissing(
                "Denim Skirt",
                "Casual denim skirt.",
                69.99,
                12,
                4.0,
                "https://picsum.photos/300/400?random=18",
                "k",
                womenSkirt
        );

        saveProductIfMissing(
                "Classic Women Shirt",
                "Classic cotton shirt.",
                89.99,
                21,
                4.1,
                "https://picsum.photos/300/400?random=19",
                "k",
                womenShirt
        );

        saveProductIfMissing(
                "Oversized Women Shirt",
                "Relaxed oversized shirt.",
                99.99,
                11,
                4.0,
                "https://picsum.photos/300/400?random=20",
                "k",
                womenShirt
        );

        saveProductIfMissing(
                "Basic Hoodie",
                "Soft fleece hoodie.",
                109.99,
                16,
                4.6,
                "https://picsum.photos/300/400?random=11",
                "k",
                womenSweater
        );

        saveProductIfMissing(
                "Women Knit Sweater",
                "Warm knitted sweater.",
                119.99,
                13,
                4.4,
                "https://picsum.photos/300/400?random=21",
                "k",
                womenSweater
        );

        saveProductIfMissing(
                "Women Black Trousers",
                "Elegant black trousers.",
                109.99,
                15,
                4.3,
                "https://picsum.photos/300/400?random=22",
                "k",
                womenTrousers
        );

        saveProductIfMissing(
                "Women Wide Leg Trousers",
                "Comfortable wide-leg trousers.",
                129.99,
                10,
                4.4,
                "https://picsum.photos/300/400?random=23",
                "k",
                womenTrousers
        );

        saveProductIfMissing(
                "Men White Sneakers",
                "Everyday white sneakers.",
                119.99,
                20,
                4.5,
                "https://picsum.photos/300/400?random=9",
                "e",
                menShoes
        );

        saveProductIfMissing(
                "Men Running Shoes",
                "Lightweight running shoes.",
                139.99,
                14,
                4.7,
                "https://picsum.photos/300/400?random=10",
                "e",
                menShoes
        );

        saveProductIfMissing(
                "Leather Jacket",
                "Classic leather jacket.",
                219.99,
                6,
                4.8,
                "https://picsum.photos/300/400?random=7",
                "e",
                menJacket
        );

        saveProductIfMissing(
                "Denim Jacket",
                "Casual denim jacket.",
                179.99,
                9,
                4.6,
                "https://picsum.photos/300/400?random=8",
                "e",
                menJacket
        );

        saveProductIfMissing(
                "Classic Men Shirt",
                "Classic formal shirt.",
                99.99,
                18,
                4.2,
                "https://picsum.photos/300/400?random=24",
                "e",
                menShirt
        );

        saveProductIfMissing(
                "Slim Fit Men Shirt",
                "Modern slim-fit shirt.",
                109.99,
                14,
                4.3,
                "https://picsum.photos/300/400?random=25",
                "e",
                menShirt
        );

        saveProductIfMissing(
                "Men Knit Sweater",
                "Warm knitted sweater.",
                119.99,
                13,
                4.4,
                "https://picsum.photos/300/400?random=26",
                "e",
                menSweater
        );

        saveProductIfMissing(
                "Men Basic Sweater",
                "Comfortable everyday sweater.",
                99.99,
                19,
                4.1,
                "https://picsum.photos/300/400?random=27",
                "e",
                menSweater
        );

        saveProductIfMissing(
                "Blue Jeans",
                "Slim fit blue jeans.",
                89.99,
                15,
                4.4,
                "https://picsum.photos/300/400?random=5",
                "e",
                menTrousers
        );

        saveProductIfMissing(
                "Black Jeans",
                "Modern black denim jeans.",
                99.99,
                12,
                4.5,
                "https://picsum.photos/300/400?random=6",
                "e",
                menTrousers
        );

        saveProductIfMissing(
                "Men Classic T-Shirt",
                "Classic cotton t-shirt for men.",
                49.99,
                22,
                4.5,
                "https://picsum.photos/300/400?random=28",
                "e",
                menTshirt
        );

        saveProductIfMissing(
                "Men Oversized T-Shirt",
                "Relaxed oversized t-shirt for men.",
                59.99,
                16,
                4.6,
                "https://picsum.photos/300/400?random=29",
                "e",
                menTshirt
        );
    }

    private Category getOrCreateCategory(
            String title,
            String gender,
            String code,
            Double rating,
            String img
    ) {
        return categoryRepository.findByCode(code)
                .map(existingCategory -> {
                    existingCategory.setTitle(title);
                    existingCategory.setGender(gender);
                    existingCategory.setRating(rating);
                    existingCategory.setImg(img);

                    return categoryRepository.save(existingCategory);
                })
                .orElseGet(() -> categoryRepository.save(
                        new Category(
                                null,
                                title,
                                gender,
                                code,
                                rating,
                                img
                        )
                ));
    }

    private void saveProductIfMissing(
            String name,
            String description,
            Double price,
            Integer stock,
            Double rating,
            String imageUrl,
            String gender,
            Category category
    ) {
        if (productRepository.existsByName(name)) {
            return;
        }

        Product product = new Product(
                null,
                name,
                description,
                price,
                stock,
                rating,
                List.of(new ProductImage(imageUrl)),
                gender,
                category,
                "Bandage"
        );

        productRepository.save(product);
    }
}