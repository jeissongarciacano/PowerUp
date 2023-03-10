package com.powerup.square.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class OrderResponse {
    private Long id;
    private Long idClient;
    private Long idChef;
    private Date date;
    private List<OrderPlateResponse> orderPlateResponses;
    private List<Long> amount;
}
