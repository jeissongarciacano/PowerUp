package com.powerup.user.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateUpdatingRequest {
    private Long id;
    private String description;
    private Long price;
    private Long idOwner;

}
