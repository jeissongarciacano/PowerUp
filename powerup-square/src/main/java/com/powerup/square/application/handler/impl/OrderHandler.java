package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.*;
import com.powerup.square.application.handler.IOrderHandler;
import com.powerup.square.application.mapper.IPlateResponseMapper;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.api.IOrderPlatesServicePort;
import com.powerup.square.domain.api.IOrderServicePort;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.exception.NoDataFoundException;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.spi.IOrderPersistencePort;
import com.powerup.square.domain.spi.IOrderPlatesPersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class OrderHandler implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    private final IOrderPersistencePort orderPersistencePort;
    private final IOrderPlatesPersistencePort orderPlatesPersistencePort;
    private final IOrderPlatesServicePort orderPlatesServicePort;
    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final IPlateServicePort plateServicePort;
    private final IPlateResponseMapper plateResponseMapper;
    private final IEmployeeServicePort employeeServicePort;

    public OrderHandler(IOrderServicePort orderServicePort, IOrderPersistencePort orderPersistencePort, IOrderPlatesPersistencePort orderPlatesPersistencePort, IOrderPlatesServicePort orderPlatesServicePort, IRestaurantPersistencePort iRestaurantPersistencePort, IPlateServicePort plateServicePort, IPlateResponseMapper plateResponseMapper, IEmployeeServicePort employeeServicePort) {
        this.orderServicePort = orderServicePort;
        this.orderPersistencePort = orderPersistencePort;
        this.orderPlatesPersistencePort = orderPlatesPersistencePort;
        this.orderPlatesServicePort = orderPlatesServicePort;
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
        this.plateServicePort = plateServicePort;
        this.plateResponseMapper = plateResponseMapper;
        this.employeeServicePort = employeeServicePort;
    }

    @Override
    public void saveOrder(OrderRequest orderRequest) {
        if(!iRestaurantPersistencePort.existById(orderRequest.getIdRestaurant())) throw new NoDataFoundException();
        else if (existsByIdClientAndState(orderRequest.getIdClient(), "PENDIENTE") ||
                existsByIdClientAndState(orderRequest.getIdClient(), "EN_PREPARACION") ||
                existsByIdClientAndState(orderRequest.getIdClient(), "LISTO")) throw new NoDataFoundException();
        else{
            Date date = new Date();
            Order order = new Order(-1L, orderRequest.getIdClient(), date, "PENDIENTE", null, orderRequest.getIdRestaurant());
            Order orderSaved = orderServicePort.saveOrder(order);
            List<OrderPlates> orderPlates = new ArrayList<>();
            for (int i = 0; i < orderRequest.getIdPlates().size(); i++) {
                Plate plate = plateServicePort.getPlate(orderRequest.getIdPlates().get(i));
                if(Objects.equals(plate.getRestaurant().getId(), orderSaved.getIdRestaurant())){
                    OrderPlates orderPlatesAux = new OrderPlates(orderSaved, plate , orderRequest.getAmountPlates().get(i));
                    orderPlates.add(orderPlatesAux);
                }
            }
            if(orderPlates.isEmpty()) throw new NoDataFoundException();
            else orderPlatesServicePort.saveOrderPlates(orderPlates);
        }
    }

    @Override
    public List<OrderResponse> getOrders(OrderListRequest orderListRequest) {
        orderListRequest.setIdEmployee(employeeServicePort.getEmployee(orderListRequest.getIdEmployee()).getRestaurant().getId());
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<Order> orders = orderServicePort.getAllOrder(orderListRequest);
        for (int i = 0; i < orders.size(); i++) {
            List<OrderPlates> orderPlates = getOrderPlatesById(orders.get(i).getId());
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(orders.get(i).getId());
            orderResponse.setDate(orders.get(i).getDate());
            orderResponse.setIdChef(orders.get(i).getIdChef());
            orderResponse.setIdClient(orders.get(i).getIdClient());
            orderResponse.setOrderPlateResponses(new ArrayList<>());
            for (int j = 0; j < orderPlates.size(); j++) {
                OrderPlateResponse orderPlateResponse = plateResponseMapper.toOrderPlateResponse(orderPlates.get(j).getPlate());
                orderPlateResponse.setAmount(orderPlates.get(j).getAmount());
                orderResponse.getOrderPlateResponses().add(orderPlateResponse);
            }
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

    @Override
    public void changeState(String state) {

    }

    @Override
    public void cancelOrder(Long id) {

    }

    @Override
    public boolean existsByIdClientAndState(Long idClient, String state) {
        return orderServicePort.existsByIdClientAndState(idClient,state);
    }

    @Override
    public List<OrderPlates> getOrderPlatesById(Long id) {
        return orderPlatesServicePort.getAllOrderPlatesByOrderId(id);
    }
}
