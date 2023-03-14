package com.powerup.square.domain.usecase;

import com.powerup.square.application.dto.order.OrderListRequest;
import com.powerup.square.domain.api.IOrderServicePort;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.spi.IOrderPersistencePort;

import java.util.List;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }
    @Override
    public Order saveOrder(Order order) {
        return orderPersistencePort.saveOrder(order);
    }
    @Override
    public List<Order> getAllOrder(Long amount, Long page, String sort, Long idRestaurant, String state) {
        return orderPersistencePort.getAllOrder(amount, page, sort, idRestaurant, state);
    }
    @Override
    public Order getOrder(Long id) {
        return orderPersistencePort.getOrder(id);
    }
    @Override
    public void cancelOrder(Long id) {
        orderPersistencePort.cancelOrder(id);
    }
    @Override
    public void updateOrder(Order order) {
        orderPersistencePort.updateOrder(order);
    }
    @Override
    public Order getOrderByClientId(Long idClient) {
        return orderPersistencePort.getOrderByClientId(idClient);
    }
    @Override
    public boolean existsByIdClientAndState(Long idClient, String state) {
        return orderPersistencePort.existsByIdClientAndState(idClient, state);
    }
}

