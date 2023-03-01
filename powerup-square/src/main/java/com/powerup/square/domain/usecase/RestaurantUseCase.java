package com.powerup.square.domain.usecase;

import com.powerup.square.domain.api.IRestaurantServicePort;
import com.powerup.square.domain.exception.NoDataFoundException;
import com.powerup.square.domain.exception.RestaurantAlreadyExistsException;
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
        if(existByName(restaurant.getName())) throw new RestaurantAlreadyExistsException();
        restaurantPersistencePort.saveRestaurant(restaurant);
    }
    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantPersistencePort.getAllRestaurant();
    }
    @Override
    public Restaurant getRestaurant(Long id) {
        if(!existById(id)) throw new NoDataFoundException();
        return restaurantPersistencePort.getRestaurant(id);
    }

    @Override
    public boolean existByName(String name) {
        return restaurantPersistencePort.existByName(name);
    }

    @Override
    public boolean existById(Long id) {
        return restaurantPersistencePort.existById(id);
    }
}
