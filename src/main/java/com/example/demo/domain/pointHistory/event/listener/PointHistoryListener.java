package com.example.demo.domain.pointHistory.event.listener;

import com.example.demo.domain.orders.event.event.OrderCreateEvent;
import com.example.demo.domain.pointHistory.entity.PointHistory;
import com.example.demo.domain.pointHistory.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PointHistoryListener {

    private final PointHistoryRepository repository;

    @EventListener
    @Transactional
    public void handleOrderCreateEvent(OrderCreateEvent event) {

        int changeAmount = (int) (event.getTotalPrice() * 0.1);

        PointHistory pointHistory = PointHistory.builder()
            .userId(event.getUserId())
            .changeAmount(changeAmount)
            .reason("물품 구매")
            .build();

        repository.save(pointHistory);
    }
}
