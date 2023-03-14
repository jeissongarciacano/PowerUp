package com.powerup.square.application.dto.plate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivatePlateRequest {
    private Long id;
    private Long idOwner;
}
