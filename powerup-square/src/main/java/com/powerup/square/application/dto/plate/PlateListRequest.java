package com.powerup.square.application.dto.plate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlateListRequest {
    private Long amount;
    private Long page;
    private String sort;
    private Long idRestaurant;
}
