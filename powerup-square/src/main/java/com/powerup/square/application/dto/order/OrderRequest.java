package com.powerup.square.application.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class OrderRequest {
    private Long idClient;
    private List<PlateOrderRequest> plateOrderRequests;
    private Long idRestaurant;

}
