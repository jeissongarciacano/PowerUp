package com.powerup.square.domain.spi;

import com.powerup.square.application.dto.order.OrderListRequest;
import com.powerup.square.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {
    Order saveOrder(Order order);
    List<Order> getAllOrder(Long amount, Long page, String sort, Long idRestaurant, String state);
    Order getOrder(Long id);
    void cancelOrder(Long id);
    void updateOrder(Order order);
    Order getOrderByClientId(Long idClient);
    boolean existsByIdClientAndState(Long idClient, String state);
    boolean existsByIdAndState(Long id, String state);
    List<Order> getAllOrderByIdEmployee(Long idEmployee,Long amount, Long page, String sort, String state);
}
