package com.example.demo.domain.orders.dto.response;

import com.example.demo.domain.orders.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private Long userId;
    private int totalPrice;

    public static OrderResponse of(Orders order) {
        return new OrderResponse(
            order.getId(),
            order.getUser().getId(),
            order.getTotalPrice()
        );
    }
}
