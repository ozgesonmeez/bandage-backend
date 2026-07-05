package com.ozgesonmez.bandagebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Double rating;
    private List<ProductImage> images;
    private String gender;
    private Category category;
    private String brand;
}