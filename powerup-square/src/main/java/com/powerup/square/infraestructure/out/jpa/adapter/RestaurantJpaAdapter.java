package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.Restaurant;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import com.powerup.square.infraestructure.out.jpa.mapper.IRestaurantMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantMapper restaurantMapper;

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantMapper.toRestaurant(restaurantRepository.save(restaurantMapper.toEntity(restaurant)));
    }

    @Override
    public List<Restaurant> getAllRestaurant(Long amount, Long page, String sort) {
        Pageable pageable = PageRequest.of(page.intValue(),
                amount.intValue(),
                Sort.by(sort).descending());
        return restaurantRepository.findAll(pageable).stream().map(restaurantMapper::toRestaurant).collect(Collectors.toList());
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantMapper.toRestaurant(restaurantRepository.findById(id).get());
    }

    @Override
    public Restaurant getRestaurantByIdOwner(Long idOwner) {
        return restaurantMapper.toRestaurant(restaurantRepository.findByIdOwner(idOwner));
    }

    @Override
    public boolean existByName(String name) {
        return restaurantRepository.existsByName(name);
    }

    @Override
    public boolean existById(Long id) {
        return restaurantRepository.existsById(id);
    }

    @Override
    public boolean existByIdOwner(Long idOwner) {
        return restaurantRepository.existsByIdOwner(idOwner);
    }

}
