package com.powerup.square.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RestaurantResponse {
    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private Long idOwner;
    @NotBlank
    private String phone;
    @NotBlank
    private String urlLogo;
    @NotBlank
    private String nit;
}
