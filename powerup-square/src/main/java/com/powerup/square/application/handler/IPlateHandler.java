package com.powerup.square.application.handler;

import com.powerup.square.application.dto.PlateRequest;
import com.powerup.square.application.dto.PlateResponse;

public interface IPlateHandler {

    void savePlate(PlateRequest plateRequest);

    PlateResponse getPlate(Long id);

}
