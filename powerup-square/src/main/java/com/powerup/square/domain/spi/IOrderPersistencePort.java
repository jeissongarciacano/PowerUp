package com.powerup.square.domain.spi;

import com.powerup.square.application.dto.OrderListRequest;
import com.powerup.square.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {

    Order saveOrder(Order order);
    List<Order> getAllOrder(OrderListRequest orderListRequest);
    Order getOrder(Long id);
    void cancelOrder(Long id);
    void updateOrder(Order order);
    Order getOrderByClientId(Long idClient);
    boolean existsByIdClientAndState(Long idClient, String state);
}
