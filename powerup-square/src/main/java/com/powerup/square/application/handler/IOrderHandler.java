package com.powerup.square.application.handler;

import com.powerup.square.application.dto.order.OrderListRequest;
import com.powerup.square.application.dto.order.OrderRequest;
import com.powerup.square.application.dto.order.OrderResponse;
import com.powerup.square.domain.model.OrderPlates;

import java.util.List;

public interface IOrderHandler {
    void saveOrder(OrderRequest orderRequest);
    List<OrderResponse> getOrders(Long amount, Long page, String sort, Long idEmployee, String state);
    void changeState(String state);
    void cancelOrder(Long id);
    boolean existsByIdClientAndState(Long idClient, String state);
    List<OrderPlates> getOrderPlatesById(Long id);
}
