package com.example.demo.domain.orders.service;

import com.example.demo.domain.orderDetail.dto.request.OrderDetailRequest;
import com.example.demo.domain.orders.dto.request.OrderRequest;
import com.example.demo.domain.orders.dto.response.OrderResponse;
import com.example.demo.domain.orders.entity.Orders;
import com.example.demo.domain.orders.event.event.OrderCreateEvent;
import com.example.demo.domain.orders.repository.OrderRepository;
import com.example.demo.domain.users.entity.Users;
import com.example.demo.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest, Long userId) {

        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        int totalPrice = orderRequest.getOrderDetails().stream()
            .mapToInt(OrderDetailRequest::getPrice)
            .sum();

        Orders order = Orders.builder()
            .user(user)
            .totalPrice(totalPrice)
            .usedPoint(orderRequest.getUsedPoint())
            .build();

        Orders savedOrder = orderRepository.save(order);
        user.usePoint(orderRequest.getUsedPoint());

        applicationEventPublisher.publishEvent(new OrderCreateEvent(
            userId, savedOrder.getTotalPrice()
        ));

        return OrderResponse.of(savedOrder);
    }
}
