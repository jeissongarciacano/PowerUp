package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.application.dto.plate.PlateListRequest;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.infraestructure.out.jpa.mapper.IPlateMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IPlateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlateJpaAdapter implements IPlatePersistencePort{
    private final IPlateRepository plateRepository;
    private final IPlateMapper plateMapper;

    @Override
    public Plate savePlate(Plate plate){
        return plateMapper.toPlate(plateRepository.save(plateMapper.toEntity(plate)));
    }

    @Override
    public List<Plate> getAllPlates(Long amount, Long page, String sort, Long idRestaurant) {
        Pageable pagination = PageRequest.of(page.intValue(),
                amount.intValue(),
                Sort.by(sort).descending());
        return plateRepository.findByIdRestaurant(idRestaurant, pagination).stream()
                .map(plateEntity -> plateMapper.toPlate(plateEntity)).collect(Collectors.toList());
    }

    @Override
    public Plate getPlate(Long id) {
        return plateMapper.toPlate(plateRepository.findById(id).get());
    }

    @Override
    public Plate updatePlate(Plate plate) {
        return plateMapper.toPlate(plateRepository.save(plateMapper.toEntity(plate)));
    }

    @Override
    public void deletePlate(Long id) {
        plateRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return plateRepository.existsById(id);
    }

    @Override
    public boolean existByName(String name) {
        return plateRepository.existsByName(name);
    }

}
