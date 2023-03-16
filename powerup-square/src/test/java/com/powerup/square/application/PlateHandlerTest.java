package com.powerup.square.application;

import com.powerup.square.application.dto.plate.ActivatePlateRequest;
import com.powerup.square.application.dto.plate.PlateRequest;
import com.powerup.square.application.dto.plate.PlateUpdatingRequest;
import com.powerup.square.application.handler.IRestaurantHandler;
import com.powerup.square.application.handler.impl.PlateHandler;
import com.powerup.square.application.mapper.IPlateRequestMapper;
import com.powerup.square.application.mapper.IPlateResponseMapper;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.model.Category;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.spi.ICategoryPersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PlateHandlerTest {
    @InjectMocks
    PlateHandler plateHandler;

    @Mock
    IPlateServicePort iPlateServicePort;
    @Mock
    IRestaurantPersistencePort iRestaurantPersistencePort;
    @Mock
    ICategoryPersistencePort iCategoryPersistencePort;
    @Mock
    IPlateRequestMapper iPlateRequestMapper;
    @Mock
    IPlateResponseMapper iPlateResponseMapper;

    @Test
    void savePlate() {

        Plate plate = SavePlateHandlerDataTest.obtainPlate();
        plate.setRestaurant(iRestaurantPersistencePort.getRestaurantByIdOwner(anyLong()));
        plate.setCategory(iCategoryPersistencePort.getCategory(anyLong()));
        plate.setId(anyLong());
        PlateRequest plateRequest = SavePlateHandlerDataTest.obtainPlateRequest();
        when(iPlateRequestMapper.toPlate(any(PlateRequest.class))).thenReturn(SavePlateHandlerDataTest.obtainPlate());
        when(iRestaurantPersistencePort.existByIdOwner(anyLong())).thenReturn(true);
        when(iRestaurantPersistencePort.getRestaurantByIdOwner(anyLong())).thenReturn(SaveRestaurantHandlerDataTest.obtainRestaurant());
        when(iCategoryPersistencePort.getCategory(anyLong())).thenReturn(new Category(1L, "pastas", "pastas"));
        plateHandler.savePlate(plateRequest);

    }

    @Test
    void getPlate() {
        Plate plate = SavePlateHandlerDataTest.obtainPlate();

        when(iPlateServicePort.getPlate(anyLong())).thenReturn(plate);
        plateHandler.getPlate(anyLong());

        verify(iPlateResponseMapper).toPlateResponse(plate);
    }

    @Test
    void updatePlate() {
        Plate plate = SavePlateHandlerDataTest.obtainPlate();
        PlateUpdatingRequest plateUpdatingRequest = SavePlateHandlerDataTest.obtainPlateUpdatingRequest();

        when(iPlateServicePort.getPlate(anyLong())).thenReturn(plate);
        when(iPlateServicePort.getPlate(plateUpdatingRequest.getId()).getRestaurant().getIdOwner()).thenReturn(1L);
        plateHandler.updatePlate(plateUpdatingRequest);

        verify(iPlateServicePort).updatePlate(plate);

    }
    @Test
    void activatePlate(){
        Plate plate = SavePlateHandlerDataTest.obtainPlate();
        ActivatePlateRequest activatePlateRequest = SavePlateHandlerDataTest.obtainActivatePlateRequest();

        when(iPlateServicePort.getPlate(anyLong())).thenReturn(plate);
        plateHandler.activePlate(activatePlateRequest);
        verify(iPlateServicePort).updatePlate(plate);
    }
}