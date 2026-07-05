package com.ozgesonmez.bandagebackend.dto;

import com.ozgesonmez.bandagebackend.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private List<Product> products;
    private Integer total;
    private Integer limit;
    private Integer offset;
}