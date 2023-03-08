package com.powerup.square.application.handler;

import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.domain.model.Order;

import java.util.List;

public interface IOrderHandler {
    void saveOrder(OrderRequest orderRequest);
    List<Order> getOrders();
    void changeState(String state);
    void cancelOrder(Long id);
}
