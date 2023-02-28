package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.PlateRequest;
import com.powerup.square.application.dto.PlateResponse;
import com.powerup.square.application.dto.PlateUpdatingRequest;
import com.powerup.square.application.handler.IPlateHandler;
import com.powerup.square.application.mapper.IPlateRequestMapper;
import com.powerup.square.application.mapper.IPlateResponseMapper;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.model.Plate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PlateHandler implements IPlateHandler {

    private final IPlateServicePort iPlateServicePort;
    private final IPlateRequestMapper iPlateRequestMapper;
    private final IPlateResponseMapper iPlateResponseMapper;

    public PlateHandler(IPlateServicePort iPlateServicePort, IPlateRequestMapper iPlateRequestMapper, IPlateResponseMapper iPlateResponseMapper) {
        this.iPlateServicePort = iPlateServicePort;
        this.iPlateRequestMapper = iPlateRequestMapper;
        this.iPlateResponseMapper = iPlateResponseMapper;
    }


    @Override
    public void savePlate(PlateRequest plateRequest) {
        Plate plate = iPlateRequestMapper.toPlate(plateRequest);
        plate.setActive(true);
        plate.setId(-1L);
        iPlateServicePort.savePlate(plate);

    }

    @Override
    public PlateResponse getPlate(Long id) {
        Plate plate = iPlateServicePort.getPlate(id);
        return iPlateResponseMapper.toPlateResponse(plate);
    }

    @Override
    public void updatePlate(PlateUpdatingRequest plateUpdatingRequest) {
        Plate plate = iPlateServicePort.getPlate(plateUpdatingRequest.getId());
        plate.setDescription(plate.getDescription());
        plate.setPrice(plate.getPrice());
        iPlateServicePort.updatePlate(plate);
    }
}
