package com.ozgesonmez.bandagebackend.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderRequest(

        @JsonProperty("address_id")
        Long addressId,

        @JsonProperty("order_date")
        Instant orderDate,

        @JsonProperty("card_no")
        String cardNo,

        @JsonProperty("card_name")
        String cardName,

        @JsonProperty("card_expire_month")
        Integer cardExpireMonth,

        @JsonProperty("card_expire_year")
        Integer cardExpireYear,

        @JsonProperty("card_ccv")
        Integer cardCcv,

        BigDecimal price,

        List<OrderProductRequest> products

) {
}