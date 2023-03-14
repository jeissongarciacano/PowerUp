package com.powerup.square.application.dto.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RestaurantResponse {

    private Long id;
    private String name;
    private String urlLogo;

}
