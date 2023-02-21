package com.PowerUp.Square.domain.usecase;

import com.PowerUp.Square.domain.api.IRestaurantServicePort;
import com.PowerUp.Square.domain.spi.IRestaurantPersistencePort;
import com.PowerUp.Square.domain.model.Restaurant;

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
