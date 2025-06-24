package com.example.demo.domain.orders.event.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCreateEvent {

    private Long userId;
    private int totalPrice;
}
