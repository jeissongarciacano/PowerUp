package com.powerup.square.application;

import com.powerup.square.application.dto.PlateRequest;
import com.powerup.square.application.dto.PlateUpdatingRequest;
import com.powerup.square.application.handler.impl.PlateHandler;
import com.powerup.square.application.mapper.IPlateRequestMapper;
import com.powerup.square.application.mapper.IPlateResponseMapper;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.spi.ICategoryPersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        //Given
        Plate plate = SavePlateHandlerDataTest.obtainPlate();
        PlateRequest plateRequest = SavePlateHandlerDataTest.obtainPlateRequest();

        //When
        when(iPlateRequestMapper.toPlate(plateRequest)).thenReturn(plate);
//        when(!iRestaurantPersistencePort.existByIdOwner(anyLong()))
//                .thenThrow(new NoDataFoundException())
//                        .thenReturn(Optional.empty().isPresent());
        plate.setRestaurant(iRestaurantPersistencePort.getRestaurantByIdOwner(anyLong()));
        plate.setCategory(iCategoryPersistencePort.getCategory(anyLong()));
        plate.setId(anyLong());

        plateHandler.savePlate(plateRequest);


        //Then
        verify(iPlateServicePort).savePlate(plate);


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
        //falla por validacion  de propietario
        Plate plate = SavePlateHandlerDataTest.obtainPlate();
        PlateUpdatingRequest plateUpdatingRequest = SavePlateHandlerDataTest.obtainPlateUpdatingRequest();

        when(iPlateServicePort.getPlate(anyLong())).thenReturn(plate);
        plateHandler.updatePlate(plateUpdatingRequest);

        verify(iPlateServicePort).updatePlate(plate);

    }
}