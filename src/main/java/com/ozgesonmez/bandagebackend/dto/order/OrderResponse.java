package com.ozgesonmez.bandagebackend.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(

        Long id,

        @JsonProperty("order_date")
        Instant orderDate,

        @JsonProperty("address_id")
        Long addressId,

        BigDecimal price,

        List<OrderProductResponse> products

) {
}