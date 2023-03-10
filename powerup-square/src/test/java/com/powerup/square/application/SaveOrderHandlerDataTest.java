package com.powerup.square.application;

import com.powerup.square.application.dto.OrderListRequest;
import com.powerup.square.application.dto.OrderRequest;
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
                1L );
    }

    public static OrderRequest obtainOrderRequest() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIdClient(2L);
        orderRequest.setIdRestaurant(1L);
        List<Long> amountlist = new ArrayList<>();
        amountlist.add(1L);
        List<Long> plateslist = new ArrayList<>();
        plateslist.add(1L);
        orderRequest.setAmountPlates(amountlist);
        orderRequest.setIdPlates(plateslist);
        return  orderRequest;
    }

    public static OrderListRequest obtainOrderListRequest() {
        OrderListRequest orderListRequest = new OrderListRequest();
        orderListRequest.setIdEmployee(1L);
        orderListRequest.setState("LISTO");
        orderListRequest.setSort("id");
        orderListRequest.setPage(0L);
        return orderListRequest;
    }

    public static List<Order> obtainOrderList() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order(1L,1L,new Date(), "LISTO", 1L, 1L);
        orders.add(order);
        return  orders;
    }

    public static List<OrderPlates> obtainOrderPlateList() {
        List<OrderPlates> orderPlates = new ArrayList<>();
        OrderPlates orderPlates1 = new OrderPlates(obtainOrder(), SavePlateHandlerDataTest.obtainPlate(), 2L);
        orderPlates.add(orderPlates1);
        return  orderPlates;
    }
}
