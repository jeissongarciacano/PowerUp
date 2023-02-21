package com.PowerUp.Square.infraestructure.out.jpa.adapter;

import com.PowerUp.Square.infraestructure.out.jpa.entity.PlateEntity;
import com.PowerUp.Square.infraestructure.out.jpa.mapper.IPlateMapper;
import com.PowerUp.Square.infraestructure.out.jpa.repository.IPlateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlateJpaAdapter {
    private final IPlateRepository plateRepository;
    private final IPlateMapper plateMapper;

    public PlateEntity savePlateEntity(PlateEntity PlateEntity){
        return plateRepository.save(PlateEntity);
    }
    public List<PlateEntity> getAllPlate(){
        return plateRepository.findAll();
    }

    public void deletePlate(Long id){
        plateRepository.deleteById(id);
    }

    public PlateEntity editPlate(PlateEntity plateEntity){
        if(plateRepository.existsById(plateEntity.getId())){
            return plateRepository.save(plateEntity);
        }
        return null;
    }

    public boolean existByID(Long id) {
        return plateRepository.existsById(id);
    }
}
