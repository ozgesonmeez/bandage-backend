package com.ozgesonmez.bandagebackend.service;

import com.ozgesonmez.bandagebackend.dto.order.OrderProductRequest;
import com.ozgesonmez.bandagebackend.dto.order.OrderRequest;
import com.ozgesonmez.bandagebackend.entity.AppUser;
import com.ozgesonmez.bandagebackend.entity.CustomerOrder;
import com.ozgesonmez.bandagebackend.entity.OrderItem;
import com.ozgesonmez.bandagebackend.entity.Product;
import com.ozgesonmez.bandagebackend.repository.CustomerOrderRepository;
import com.ozgesonmez.bandagebackend.repository.OrderItemRepository;
import com.ozgesonmez.bandagebackend.repository.ProductRepository;
import com.ozgesonmez.bandagebackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.ozgesonmez.bandagebackend.dto.order.OrderProductResponse;
import com.ozgesonmez.bandagebackend.dto.order.OrderResponse;

import java.util.List;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final CustomerOrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderService(
            CustomerOrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository,
            UserRepository userRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Long createOrder(
            OrderRequest request,
            String userEmail
    ) {
        AppUser user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() ->
                        new RuntimeException("Kullanıcı bulunamadı.")
                );

        String cleanCardNumber =
                request.cardNo().replaceAll("\\s", "");

        CustomerOrder order = new CustomerOrder();
        order.setUser(user);
        order.setAddressId(request.addressId());
        order.setOrderDate(request.orderDate());
        order.setCardLastFour(
                cleanCardNumber.substring(
                        cleanCardNumber.length() - 4
                )
        );
        order.setCardName(request.cardName());
        order.setCardExpireMonth(
                request.cardExpireMonth()
        );
        order.setCardExpireYear(
                request.cardExpireYear()
        );
        order.setPrice(request.price());

        CustomerOrder savedOrder =
                orderRepository.save(order);

        for (OrderProductRequest itemRequest
                : request.products()) {

            Product product = productRepository
                    .findById(itemRequest.productId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Ürün bulunamadı: "
                                            + itemRequest.productId()
                            )
                    );

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setCount(itemRequest.count());
            orderItem.setDetail(itemRequest.detail());
            orderItem.setUnitPrice(
                    BigDecimal.valueOf(product.getPrice())
            );

            orderItemRepository.save(orderItem);
        }

        return savedOrder.getId();
    }
    @Transactional
    public List<OrderResponse> getOrders(
            String userEmail
    ) {
        AppUser user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Kullanıcı bulunamadı."
                        )
                );

        return orderRepository
                .findAllByUserOrderByOrderDateDesc(user)
                .stream()
                .map(order -> {

                    List<OrderProductResponse> products =
                            orderItemRepository
                                    .findAllByOrder(order)
                                    .stream()
                                    .map(item ->
                                            new OrderProductResponse(
                                                    item.getProduct().getId(),
                                                    item.getCount(),
                                                    item.getDetail()
                                            )
                                    )
                                    .toList();

                    return new OrderResponse(
                            order.getId(),
                            order.getOrderDate(),
                            order.getAddressId(),
                            order.getPrice(),
                            products
                    );
                })
                .toList();
    }
}