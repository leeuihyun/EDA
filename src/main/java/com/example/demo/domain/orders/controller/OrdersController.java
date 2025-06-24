package com.example.demo.domain.orders.controller;

import com.example.demo.domain.orders.dto.request.OrderRequest;
import com.example.demo.domain.orders.dto.response.OrderResponse;
import com.example.demo.domain.orders.service.OrderService;
import com.example.demo.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> order(
        @RequestBody @Valid OrderRequest orderRequest, @AuthenticationPrincipal Long userId) {

        OrderResponse response = orderService.createOrder(orderRequest, userId);

        return ResponseEntity.ok(ApiResponse.success("주문이 성공했습니다.", response));
    }
}
