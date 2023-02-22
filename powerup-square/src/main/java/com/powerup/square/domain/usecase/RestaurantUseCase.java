package com.powerup.square.domain.usecase;

import com.powerup.square.domain.api.IRestaurantServicePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import com.powerup.square.domain.model.Restaurant;

import java.util.List;
public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantPersistencePort.getAllRestaurant();
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantPersistencePort.getRestaurant(id);
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        restaurantPersistencePort.updateRestaurant(restaurant);
    }
}
