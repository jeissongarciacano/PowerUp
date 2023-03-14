package com.powerup.square.application.handler;

import com.powerup.square.application.dto.plate.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IPlateHandler {
    PlateResponse savePlate(PlateRequest plateRequest);
    PlateResponse getPlate(Long id);
    PlateResponse updatePlate(PlateUpdatingRequest plateUpdatingRequest);
    PlateResponse activePlate(ActivatePlateRequest activatePlateRequest);
    List<PlateResponse> getPlates(Long amount, Long page, String sort, Long idRestaurant);
    void deletePlate(Long id);
    boolean existById(Long id);
    boolean existByName(String name);
}
