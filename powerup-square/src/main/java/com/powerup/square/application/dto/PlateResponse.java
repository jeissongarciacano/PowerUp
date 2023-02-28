package com.powerup.square.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateResponse {

    private Long id;
    private String name;
    private Long idCategory;
    private Long description;
    private Long price;
    private Long idRestaurant;
    private String urlImage;

}
