package com.powerup.square.application;

import com.powerup.square.application.dto.order.OrderRequest;
import com.powerup.square.application.dto.order.PlateOrderRequest;
import com.powerup.square.application.dto.user.Role;
import com.powerup.square.application.dto.user.UserResponse;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaveOrderHandlerDataTest {
    public static Order obtainOrder() {
        return  new Order(1L,
                2L,
                new Date(),
                "PENDIENTE",
                null,
                100L );
    }

    public static OrderRequest obtainOrderRequest() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIdClient(2L);
        orderRequest.setIdRestaurant(1L);
        orderRequest.setPlateOrderRequests(new ArrayList<>());
        PlateOrderRequest plateOrderRequest = new PlateOrderRequest();
        plateOrderRequest.setIdPlates(SavePlateHandlerDataTest.obtainPlate().getId());
        plateOrderRequest.setAmountPlates(2L);
        orderRequest.getPlateOrderRequests().add(plateOrderRequest);
        return  orderRequest;
    }


    public static List<Order> obtainOrderList() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order(1L,1L,new Date(), "LISTO", SaveEmployeeHandlerDataTest.obtainEmployee(), 1L);
        orders.add(order);
        return  orders;
    }

    public static List<OrderPlates> obtainOrderPlateList() {
        List<OrderPlates> orderPlates = new ArrayList<>();
        OrderPlates orderPlates1 = new OrderPlates(obtainOrder(), SavePlateHandlerDataTest.obtainPlate(), 2L);
        orderPlates.add(orderPlates1);
        return  orderPlates;
    }

    public static UserResponse obtainUser() {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(1L);
        userResponse.setName("pepito");
        userResponse.setEmail("pepito@hotmail.com");
        userResponse.setPhone("3003003030");
        userResponse.setRole(new Role(1L,"Cliente", "compra"));
        userResponse.setIdDocument("100100100");
        userResponse.setLastName("perez");
        return userResponse;
    }
}
