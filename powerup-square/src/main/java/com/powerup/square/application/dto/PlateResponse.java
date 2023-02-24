package com.powerup.square.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateResponse {

    private Long id;
    private String name;
    private String idCategory;
    private String description;
    private String price;
//    private String idRestaurant;
    private String urlImage;

}
