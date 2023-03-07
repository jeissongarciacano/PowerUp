package com.powerup.user.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantListRequest {
    private Long amount;
    private Long page;
    private String sort;
}
