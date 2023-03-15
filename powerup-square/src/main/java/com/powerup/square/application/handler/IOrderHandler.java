package com.powerup.square.application.handler;

import com.powerup.square.application.dto.order.OrderRequest;
import com.powerup.square.application.dto.order.OrderResponse;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;

import java.util.List;

public interface IOrderHandler {
    void saveOrder(OrderRequest orderRequest);
    List<OrderResponse> getOrders(Long amount, Long page, String sort, Long idEmployee, String state);
    List<OrderResponse> takeOrder(Long idOrder, Long idEmployee, Long amount, Long page, String sort, String state);
    void cancelOrder(Long id, Long idClient);
    boolean existsByIdClientAndState(Long idClient, String state);
    List<OrderPlates> getOrderPlatesById(Long id);
    boolean existsByIdAndState(Long id, String state);
    List<Order> getAllOrderByIdEmployee(Long idEmployee, Long amount, Long page, String sort, String state);
    void readyToDeliver(Long idOrder, Long idEmployee);
    void deliver(Long idOrder, Long idEmployee, String pin);
}
