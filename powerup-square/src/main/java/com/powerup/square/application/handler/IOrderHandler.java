package com.powerup.square.application.handler;

import com.powerup.square.application.dto.OrderListRequest;
import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.dto.OrderResponse;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;

import java.util.List;

public interface IOrderHandler {
    void saveOrder(OrderRequest orderRequest);
    List<OrderResponse> getOrders(OrderListRequest orderListRequest);
    void changeState(String state);
    void cancelOrder(Long id);
    boolean existsByIdClientAndState(Long idClient, String state);
    List<OrderPlates> getOrderPlatesById(Long id);
}
