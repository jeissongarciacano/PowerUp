package com.powerup.square.domain.usecase;

import com.powerup.square.application.dto.plate.PlateListRequest;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.exception.NoDataFoundException;
import com.powerup.square.domain.exception.PlateAlreadyExistsException;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.domain.model.Plate;

import java.util.List;
public class PlateUseCase implements IPlateServicePort {
    private final IPlatePersistencePort platePersistencePort;
    public PlateUseCase(IPlatePersistencePort platePersistencePort) {
        this.platePersistencePort = platePersistencePort;
    }
    @Override
    public Plate savePlate(Plate plate) {
        if(existByName(plate.getName()) ) throw new PlateAlreadyExistsException();
        return platePersistencePort.savePlate(plate);
    }
    @Override
    public List<Plate> getAllPlates(Long amount, Long page, String sort, Long idRestaurant) {
        return platePersistencePort.getAllPlates(amount, page, sort, idRestaurant);
    }
    @Override
    public Plate getPlate(Long id) {
        if(!existById(id)) throw new NoDataFoundException();
        return platePersistencePort.getPlate(id);
    }
    @Override
    public Plate updatePlate(Plate plate) {
        return platePersistencePort.updatePlate(plate);
    }
    @Override
    public void deletePlate(Long id) {
        platePersistencePort.deletePlate(id);
    }
    @Override
    public boolean existById(Long id) {
        return platePersistencePort.existById(id);
    }
    @Override
    public boolean existByName(String name) {
        return platePersistencePort.existByName(name);
    }
}

