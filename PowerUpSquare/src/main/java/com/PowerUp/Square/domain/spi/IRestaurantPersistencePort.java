package com.PowerUp.Square.domain.spi;

import com.PowerUp.Square.domain.model.Restaurant;

import java.util.List;


public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurant();
    Restaurant getRestaurant(Long id);
    void updateRestaurant(Restaurant restaurant);
}
