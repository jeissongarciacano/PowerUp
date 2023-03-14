package com.powerup.square.application.handler;

import com.powerup.square.application.dto.restaurant.RestaurantResponse;
import com.powerup.square.application.dto.restaurant.RestaurantRequest;
import com.powerup.square.application.dto.restaurant.RestaurantListRequest;
import com.powerup.square.domain.model.Restaurant;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IRestaurantHandler {
    RestaurantResponse saveRestaurant(RestaurantRequest restaurantRequest);
    RestaurantResponse getRestaurant(Long id);
    List<RestaurantResponse> getRestaurants(Long amount, Long page,String sort);
    Restaurant getRestaurantByIdOwner(Long idOwner);
    boolean existByName(String name);
    boolean existById(Long id);
    boolean existByIdOwner(Long idOwner);
}
