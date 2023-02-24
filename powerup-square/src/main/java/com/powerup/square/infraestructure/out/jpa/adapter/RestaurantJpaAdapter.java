package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.Restaurant;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import com.powerup.square.infraestructure.out.jpa.entity.RestaurantEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IRestaurantMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantMapper restaurantMapper;

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantMapper.toEntity(restaurant);
        restaurantRepository.save(restaurantEntity);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return null;
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantMapper.toRestaurant(restaurantRepository.findById(id));
    }
    @Override
    public void updateRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurantMapper.toEntity(restaurant));
    }

}
