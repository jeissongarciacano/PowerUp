package com.powerup.square.domain.spi;

import com.powerup.square.application.dto.RestaurantListRequest;
import com.powerup.square.domain.model.Restaurant;

import java.util.List;


public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurant(RestaurantListRequest restaurantListRequest);
    Restaurant getRestaurant(Long id);
    Restaurant getRestaurantByIdOwner(Long idOwner);
    boolean existByName(String name);
    boolean existById(Long id);
    boolean existByIdOwner(Long idOwner);
}
