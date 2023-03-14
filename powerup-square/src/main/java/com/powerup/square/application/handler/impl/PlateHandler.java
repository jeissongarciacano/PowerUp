package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.plate.*;
import com.powerup.square.application.handler.IPlateHandler;
import com.powerup.square.application.mapper.IPlateRequestMapper;
import com.powerup.square.application.mapper.IPlateResponseMapper;
import com.powerup.square.domain.api.ICategoryServicePort;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.exception.NoDataFoundException;
import com.powerup.square.domain.model.Plate;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PlateHandler implements IPlateHandler {
    private final IPlateServicePort iPlateServicePort;
    private final RestaurantHandler restaurantHandler;
    private final ICategoryServicePort categoryServicePort;
    private final IPlateRequestMapper iPlateRequestMapper;
    private final IPlateResponseMapper iPlateResponseMapper;

    public PlateHandler(IPlateServicePort iPlateServicePort, RestaurantHandler restaurantHandler, ICategoryServicePort categoryServicePort, IPlateRequestMapper iPlateRequestMapper, IPlateResponseMapper iPlateResponseMapper) {
        this.iPlateServicePort = iPlateServicePort;
        this.restaurantHandler = restaurantHandler;
        this.categoryServicePort = categoryServicePort;
        this.iPlateRequestMapper = iPlateRequestMapper;
        this.iPlateResponseMapper = iPlateResponseMapper;
    }
    @Override
    public PlateResponse savePlate(PlateRequest plateRequest) {
        Plate plate = iPlateRequestMapper.toPlate(plateRequest);
        plate.setActive(true);
        if(!restaurantHandler.existByIdOwner(plateRequest.getIdOwner())) throw new NoDataFoundException();
        else{
            plate.setRestaurant(restaurantHandler.getRestaurantByIdOwner(plateRequest.getIdOwner()));
            plate.setCategory(categoryServicePort.getCategory(plateRequest.getIdCategory()));
            plate.setId(-1L);
            return iPlateResponseMapper.toPlateResponse(iPlateServicePort.savePlate(plate));
        }
    }
    @Override
    public PlateResponse getPlate(Long id) {
        Plate plate = iPlateServicePort.getPlate(id);
        return iPlateResponseMapper.toPlateResponse(plate);
    }
    @Override
    public PlateResponse updatePlate(PlateUpdatingRequest plateUpdatingRequest) {
        if(!restaurantHandler.existByIdOwner(plateUpdatingRequest.getIdOwner()) ||
                !Objects.equals(plateUpdatingRequest.getIdOwner(), restaurantHandler.getRestaurantByIdOwner(plateUpdatingRequest.getIdOwner()).getIdOwner()))
            throw new NoDataFoundException();
        else{
            Plate plate = iPlateServicePort.getPlate(plateUpdatingRequest.getId());
            if(Strings.isNotBlank(plateUpdatingRequest.getDescription()) || Strings.isNotEmpty(plateUpdatingRequest.getDescription())) plate.setDescription(plateUpdatingRequest.getDescription());
            if(plateUpdatingRequest.getPrice() > 0) plate.setPrice(plateUpdatingRequest.getPrice());
            return iPlateResponseMapper.toPlateResponse(iPlateServicePort.updatePlate(plate));
        }
    }
    @Override
    public PlateResponse activePlate(ActivatePlateRequest activatePlateRequest){
        if (!restaurantHandler.existByIdOwner(activatePlateRequest.getIdOwner()) ||
                !Objects.equals(activatePlateRequest.getIdOwner(), restaurantHandler.getRestaurantByIdOwner(activatePlateRequest.getIdOwner()).getIdOwner()))
            throw new NoDataFoundException();
        else{
            Plate plate = iPlateServicePort.getPlate(activatePlateRequest.getId());
            plate.setActive(!plate.isActive());
            return iPlateResponseMapper.toPlateResponse(iPlateServicePort.updatePlate(plate));
        }
    }
    @Override
    public List<PlateResponse> getPlates(Long amount, Long page, String sort, Long idRestaurant) {
        List<Plate> plates = iPlateServicePort.getAllPlates(amount, page, sort, idRestaurant);
        List<PlateResponse> plateResponses = new ArrayList<>();
        for (int i = 0; i < plates.size(); i++) {
            plateResponses.add(iPlateResponseMapper.toPlateResponse(plates.get(i)));
        }
        return plateResponses;
    }
    @Override
    public void deletePlate(Long id) {
        iPlateServicePort.deletePlate(id);
    }
    @Override
    public boolean existById(Long id) {
        return iPlateServicePort.existById(id);
    }
    @Override
    public boolean existByName(String name) {
        return iPlateServicePort.existByName(name);
    }
}
