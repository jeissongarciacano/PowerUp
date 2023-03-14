package com.powerup.square.application;

import com.powerup.square.application.dto.order.OrderListRequest;
import com.powerup.square.application.dto.order.OrderRequest;
import com.powerup.square.application.handler.impl.OrderHandler;
import com.powerup.square.application.mapper.IPlateResponseMapper;
import com.powerup.square.domain.api.IEmployeeServicePort;
import com.powerup.square.domain.api.IOrderPlatesServicePort;
import com.powerup.square.domain.api.IOrderServicePort;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.spi.IOrderPersistencePort;
import com.powerup.square.domain.spi.IOrderPlatesPersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OrderHandlerTest {
    @InjectMocks
    OrderHandler orderHandler;
    @Mock
    IOrderPlatesServicePort orderPlatesServicePort;
    @Mock
    IOrderPlatesPersistencePort orderPlatesPersistencePort;
    @Mock
    IOrderServicePort orderServicePort;
    @Mock
    IOrderPersistencePort orderPersistencePort;
    @Mock
    IRestaurantPersistencePort iRestaurantPersistencePort;
    @Mock
    IPlateServicePort plateServicePort;
    @Mock
    Plate plate;
    @Mock
    IEmployeeServicePort employeeServicePort;
    @Mock
    IPlateResponseMapper plateResponseMapper;

    @Test
    void saveOrder(){
        Order order = SaveOrderHandlerDataTest.obtainOrder();
        OrderRequest orderRequest = SaveOrderHandlerDataTest.obtainOrderRequest();

        when(iRestaurantPersistencePort.existById(orderRequest.getIdRestaurant())).thenReturn(true);
        when(orderHandler.existsByIdClientAndState(orderRequest.getIdClient(), "PENDIENTE")).thenReturn(false);
        when(orderHandler.existsByIdClientAndState(orderRequest.getIdClient(), "EN_PREPARACION")).thenReturn(false);
        when(orderHandler.existsByIdClientAndState(orderRequest.getIdClient(), "LISTO")).thenReturn(false);
        when(plateServicePort.getPlate(anyLong())).thenReturn(SavePlateHandlerDataTest.obtainPlate());
        when(orderServicePort.saveOrder(any(Order.class))).thenReturn(order);

        orderHandler.saveOrder(orderRequest);
    }
    @Test
    void getOrders(){
//        OrderListRequest orderListRequest = SaveOrderHandlerDataTest.obtainOrderListRequest();
//        when(employeeServicePort.getEmployee(anyLong())).thenReturn(SaveEmployeeHandlerDataTest.obtainEmployee());
//        when(orderServicePort.getAllOrder(any(OrderListRequest.class))).thenReturn(SaveOrderHandlerDataTest.obtainOrderList());
//        when(orderHandler.getOrderPlatesById(anyLong())).thenReturn(SaveOrderHandlerDataTest.obtainOrderPlateList());
//        when(plateResponseMapper.toOrderPlateResponse(any(Plate.class))).thenReturn(SavePlateHandlerDataTest.obtainPlateResponse());
//        assertEquals(orderHandler.getOrders(orderListRequest).get(anyInt()).getId(), 1L);
    }

}
