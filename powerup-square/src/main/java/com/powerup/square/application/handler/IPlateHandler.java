package com.powerup.square.application.handler;

import com.powerup.square.application.dto.*;

import java.util.List;

public interface IPlateHandler {
    void savePlate(PlateRequest plateRequest);
    PlateResponse getPlate(Long id);
    void updatePlate(PlateUpdatingRequest plateUpdatingRequest);
    void activePlate(ActivatePlateRequest activatePlateRequest);
    List<PlateResponse> getPlates(PlateListRequest plateListRequest);
}
