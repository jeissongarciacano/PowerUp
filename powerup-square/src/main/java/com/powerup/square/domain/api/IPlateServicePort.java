package com.powerup.square.domain.api;

import com.powerup.square.domain.model.Plate;

import java.util.List;

public interface IPlateServicePort {
    Plate savePlate(Plate plate);
    List<Plate> getAllPlates(Long amount, Long page, String sort, Long idRestaurant);
    Plate getPlate(Long id);
    Plate updatePlate(Plate plate);
    void deletePlate(Long id);
    boolean existById(Long id);
    boolean existByName(String name);

}
