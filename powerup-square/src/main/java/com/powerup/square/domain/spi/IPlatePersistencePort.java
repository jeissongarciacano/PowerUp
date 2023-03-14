package com.powerup.square.domain.spi;

import com.powerup.square.application.dto.plate.PlateListRequest;
import com.powerup.square.domain.model.Plate;

import java.util.List;

public interface IPlatePersistencePort {

    Plate savePlate(Plate plate);
    List<Plate> getAllPlates(Long amount, Long page, String sort, Long idRestaurant);
    Plate getPlate(Long id);
    Plate updatePlate(Plate plate);
    void deletePlate(Long id);
    boolean existById(Long id);
    boolean existByName(String name);

}
