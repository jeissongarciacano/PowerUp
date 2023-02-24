package com.powerup.square.domain.spi;

import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.model.Restaurant;

import java.util.List;

public interface IPlatePersistencePort {

    void savePlate(Plate plate);

    List<Plate> getAllPlates();
    Plate getPlate(Long id);
    void updatePlate(Plate plate);

}
