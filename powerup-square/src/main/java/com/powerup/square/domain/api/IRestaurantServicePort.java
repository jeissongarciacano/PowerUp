package com.powerup.square.domain.api;

import com.powerup.square.application.dto.restaurant.RestaurantListRequest;
import com.powerup.square.domain.model.Restaurant;
import java.util.List;


public interface IRestaurantServicePort {
    Restaurant saveRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurant(Long amount, Long page, String sort);
    Restaurant getRestaurant(Long id);
    Restaurant getRestaurantByIdOwner(Long idOwner);
    boolean existByName(String name);
    boolean existById(Long id);
    boolean existByIdOwner(Long idOwner);
}
