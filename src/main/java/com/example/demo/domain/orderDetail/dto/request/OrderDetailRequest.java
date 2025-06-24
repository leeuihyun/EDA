package com.example.demo.domain.orderDetail.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDetailRequest {

    private Long productId;
    private int price;
    private int quantity;
}
