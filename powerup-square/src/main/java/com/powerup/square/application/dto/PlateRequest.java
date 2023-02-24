package com.powerup.square.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class PlateRequest {

    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$", message = "The idCategory field should be numeric")
    private String idCategory;
    @NotBlank
    private String description;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$", message = "The price field should be numeric")
    private String price;
//    @NotBlank
//    @Pattern(regexp = "^[0-9]*$", message = "The idRestaurant field should be numeric")
//    private String idRestaurant;
    @NotBlank
    private String urlImage;

}
