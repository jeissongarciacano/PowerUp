package com.powerup.square.application.dto.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RestaurantListRequest {
    private Long amount;
    private Long page;
    @NotBlank
    private String sort;
}
