package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.Plate;
import com.powerup.square.infraestructure.out.jpa.entity.PlateEntity;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.infraestructure.out.jpa.mapper.IPlateMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IPlateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlateJpaAdapter implements IPlatePersistencePort{
    private final IPlateRepository plateRepository;
    private final IPlateMapper plateMapper;

    @Override
    public void savePlate(Plate plate){
        PlateEntity plateEntity = plateMapper.toEntity(plate);
        plateRepository.save(plateEntity);
    }

    @Override
    public List<Plate> getAllPlates() {
//        return plateRepository.findAll();
        return null;
    }

    @Override
    public Plate getPlate(Long id) {
        return null;
    }

    @Override
    public void updatePlate(Plate plate) {

    }

}
