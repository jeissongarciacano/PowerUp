package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.application.handler.IOrderHandler;
import com.powerup.square.domain.api.IOrderPlatesServicePort;
import com.powerup.square.domain.api.IOrderServicePort;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.exception.NoDataFoundException;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.spi.IOrderPersistencePort;
import com.powerup.square.domain.spi.IOrderPlatesPersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderHandler implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    private final IOrderPersistencePort orderPersistencePort;
    private final IOrderPlatesPersistencePort orderPlatesPersistencePort;
    private final IOrderPlatesServicePort orderPlatesServicePort;
    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final IPlateServicePort plateServicePort;

    public OrderHandler(IOrderServicePort orderServicePort, IOrderPersistencePort orderPersistencePort, IOrderPlatesPersistencePort orderPlatesPersistencePort, IOrderPlatesServicePort orderPlatesServicePort, IRestaurantPersistencePort iRestaurantPersistencePort, IPlateServicePort plateServicePort) {
        this.orderServicePort = orderServicePort;
        this.orderPersistencePort = orderPersistencePort;
        this.orderPlatesPersistencePort = orderPlatesPersistencePort;
        this.orderPlatesServicePort = orderPlatesServicePort;
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
        this.plateServicePort = plateServicePort;
    }

    @Override
    public void saveOrder(OrderRequest orderRequest) {
        if(!iRestaurantPersistencePort.existById(orderRequest.getIdRestaurant())) throw new NoDataFoundException();
        else{
            Date date = new Date();
            Order order = new Order(-1L, orderRequest.getIdClient(), date, "PENDIENTE", null, orderRequest.getIdRestaurant());
            order = orderServicePort.saveOrder(order);
            List<OrderPlates> orderPlates = new ArrayList<>();
            for (int i = 0; i < orderRequest.getIdPlates().size(); i++) {
                OrderPlates orderPlatesAux = new OrderPlates(order, plateServicePort.getPlate(orderRequest.getIdPlates().get(i)) , orderRequest.getAmountPlates().get(i));
                orderPlates.add(orderPlatesAux);
            }
            orderPlatesServicePort.saveOrderPlates(orderPlates);
        }
    }

    @Override
    public List<Order> getOrders() {
        return null;
    }

    @Override
    public void changeState(String state) {

    }

    @Override
    public void cancelOrder(Long id) {

    }
}
