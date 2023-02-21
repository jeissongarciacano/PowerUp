package com.PowerUp.Square.domain.api;

import com.PowerUp.Square.domain.model.Restaurant;
import java.util.List;


public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurant();
    Restaurant getRestaurant(Long id);
    void updateRestaurant(Restaurant restaurant);
}
