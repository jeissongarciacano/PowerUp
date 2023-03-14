package com.powerup.square.application.dto.order;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderListRequest {
    private Long amount;
    private Long page;
    private String sort;
    private Long idEmployee;
    private String state;
}
