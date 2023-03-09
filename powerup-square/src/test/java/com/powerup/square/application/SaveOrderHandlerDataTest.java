package com.powerup.square.application;

import com.powerup.square.application.dto.OrderRequest;
import com.powerup.square.domain.model.Order;

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
}
