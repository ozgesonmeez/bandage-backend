package com.ozgesonmez.bandagebackend.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderProductRequest(

        @JsonProperty("product_id")
        Long productId,

        Integer count,

        String detail

) {
}