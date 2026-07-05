package com.ozgesonmez.bandagebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Long id;
    private String title;
    private String gender;
    private String code;
    private Double rating;
}