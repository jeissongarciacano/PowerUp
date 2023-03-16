package com.powerup.square.application;

import com.powerup.square.application.dto.order.OrderRequest;
import com.powerup.square.application.handler.impl.OrderHandler;
import com.powerup.square.application.handler.impl.RestaurantHandler;
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
import com.powerup.square.infraestructure.configuration.TwilioClient;
import com.powerup.square.infraestructure.configuration.userclient.UserClient;
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
    @Mock
    RestaurantHandler restaurantHandler;
    @Mock
    UserClient userClient;

    @Test
    void saveOrder(){
        Order order = SaveOrderHandlerDataTest.obtainOrder();
        OrderRequest orderRequest = SaveOrderHandlerDataTest.obtainOrderRequest();

        when(restaurantHandler.existById(orderRequest.getIdRestaurant())).thenReturn(true);
        when(orderHandler.existsByIdClientAndState(orderRequest.getIdClient(), "PENDIENTE")).thenReturn(false);
        when(orderHandler.existsByIdClientAndState(orderRequest.getIdClient(), "EN_PREPARACION")).thenReturn(false);
        when(orderHandler.existsByIdClientAndState(orderRequest.getIdClient(), "LISTO")).thenReturn(false);
        when(plateServicePort.getPlate(anyLong())).thenReturn(SavePlateHandlerDataTest.obtainPlate());
        when(orderServicePort.saveOrder(any(Order.class))).thenReturn(order);

        orderHandler.saveOrder(orderRequest);
    }
    @Test
    void getOrders(){
        when(employeeServicePort.getEmployee(anyLong())).thenReturn(SaveEmployeeHandlerDataTest.obtainEmployee());
        when(orderServicePort.getAllOrder(anyLong(), anyLong(), anyString(), anyLong(), anyString())).thenReturn(SaveOrderHandlerDataTest.obtainOrderList());
        when(orderHandler.getOrderPlatesById(anyLong())).thenReturn(SaveOrderHandlerDataTest.obtainOrderPlateList());
        when(plateResponseMapper.toOrderPlateResponse(any(Plate.class))).thenReturn(SavePlateHandlerDataTest.obtainPlateResponse());
        assertEquals(orderHandler.getOrders(anyLong(), anyLong(), anyString(), anyLong(), anyString()).get(anyInt()).getId(), 1L);
    }
    @Test
    void takeOrder(){
        when(employeeServicePort.getEmployee(anyLong())).thenReturn(SaveEmployeeHandlerDataTest.obtainEmployee());
        when(orderHandler.existsByIdAndState(anyLong(), anyString())).thenReturn(true);
        when(orderServicePort.getOrder(anyLong())).thenReturn(SaveOrderHandlerDataTest.obtainOrder());
        when(employeeServicePort.getEmployee(anyLong())).thenReturn(SaveEmployeeHandlerDataTest.obtainEmployee());
        when(orderServicePort.getAllOrderByIdEmployee(anyLong(), anyLong(), anyLong(), anyString(), anyString())).thenReturn(SaveOrderHandlerDataTest.obtainOrderList());
        when(orderHandler.getOrderPlatesById(anyLong())).thenReturn(SaveOrderHandlerDataTest.obtainOrderPlateList());
        when(plateResponseMapper.toOrderPlateResponse(any(Plate.class))).thenReturn(SavePlateHandlerDataTest.obtainPlateResponse());

        assertEquals(orderHandler.takeOrder(anyLong(), anyLong(), anyLong(), anyLong(), anyString(), anyString()).get(anyInt()).getId(), 1L);
    }
    @Test
    void orderReady(){
        when(orderHandler.existsByIdAndState(anyLong(), anyString())).thenReturn(true);
        Order order = SaveOrderHandlerDataTest.obtainOrder();
        order.setChef(SaveEmployeeHandlerDataTest.obtainEmployee());
        when(orderServicePort.getOrder(anyLong())).thenReturn(order);
        when(userClient.getUserById(anyLong())).thenReturn(SaveOrderHandlerDataTest.obtainUser());
        orderHandler.readyToDeliver(anyLong(), 10L);
    }
    @Test
    void orderDeliver(){
        when(orderHandler.existsByIdAndState(anyLong(), anyString())).thenReturn(true);
        Order order = SaveOrderHandlerDataTest.obtainOrder();
        order.setChef(SaveEmployeeHandlerDataTest.obtainEmployee());
        when(orderServicePort.getOrder(anyLong())).thenReturn(order);
        when(userClient.getUserById(anyLong())).thenReturn(SaveOrderHandlerDataTest.obtainUser());
        orderHandler.deliver(anyLong(), 10L, "100100100");
    }
    @Test
    void cancelOrder(){
        when(orderHandler.existsByIdAndState(anyLong(), anyString())).thenReturn(true);
        when(orderHandler.existsByIdAndState(anyLong(), anyString())).thenReturn(false);
        when(orderServicePort.getOrder(anyLong())).thenReturn(SaveOrderHandlerDataTest.obtainOrder());
        orderHandler.cancelOrder(anyLong(), 2L);
    }

}
