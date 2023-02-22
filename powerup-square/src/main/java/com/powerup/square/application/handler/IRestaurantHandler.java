package com.PowerUp.Square.application.handler;

import com.PowerUp.Square.application.dto.RestaurantResponse;
import com.PowerUp.Square.application.dto.RestaurantRequest;

public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequest restaurantRequest);
    RestaurantResponse getRestaurant(Long id);
}
