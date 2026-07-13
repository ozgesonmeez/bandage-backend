package com.ozgesonmez.bandagebackend.controller;

import com.ozgesonmez.bandagebackend.dto.order.OrderRequest;
import com.ozgesonmez.bandagebackend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.ozgesonmez.bandagebackend.dto.order.OrderResponse;
import java.util.List;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(
            @RequestBody OrderRequest request,
            Authentication authentication
    ) {
        Long orderId = orderService.createOrder(
                request,
                authentication.getName()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                        "message",
                        "Sipariş başarıyla oluşturuldu.",
                        "orderId",
                        orderId
                ));
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(
            Authentication authentication
    ) {
        List<OrderResponse> orders =
                orderService.getOrders(
                        authentication.getName()
                );

        return ResponseEntity.ok(orders);
    }
}