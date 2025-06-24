package com.example.demo.domain.orders.dto.request;

import com.example.demo.domain.orderDetail.dto.request.OrderDetailRequest;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderRequest {

    @NotNull
    private List<OrderDetailRequest> orderDetails;

    @NotNull
    private int usedPoint;
}
