package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.restaurant.RestaurantListRequest;
import com.powerup.square.application.dto.restaurant.RestaurantRequest;
import com.powerup.square.application.dto.restaurant.RestaurantResponse;
import com.powerup.square.application.handler.IRestaurantHandler;
import com.powerup.square.application.mapper.IRestaurantRequestMapper;
import com.powerup.square.application.mapper.IRestaurantResponseMapper;
import com.powerup.square.domain.api.IRestaurantServicePort;
import com.powerup.square.domain.model.Restaurant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RestaurantHandler implements IRestaurantHandler {
    private final IRestaurantServicePort iRestaurantServicePort;
    private  final IRestaurantRequestMapper iRestaurantRequestMapper;
    private final IRestaurantResponseMapper iRestaurantResponseMapper;
    public RestaurantHandler(IRestaurantServicePort iRestaurantServicePort, IRestaurantRequestMapper iRestaurantRequestMapper, IRestaurantResponseMapper iRestaurantResponseMapper) {
        this.iRestaurantServicePort = iRestaurantServicePort;
        this.iRestaurantRequestMapper = iRestaurantRequestMapper;
        this.iRestaurantResponseMapper = iRestaurantResponseMapper;
    }
    @Override
    public RestaurantResponse saveRestaurant(RestaurantRequest restaurantRequest) {
        return iRestaurantResponseMapper.toRestaurantResponse(iRestaurantServicePort.saveRestaurant(iRestaurantRequestMapper.toRestaurant(restaurantRequest)));
    }
    @Override
    public RestaurantResponse getRestaurant(Long id) {
        return iRestaurantResponseMapper.toRestaurantResponse(iRestaurantServicePort.getRestaurant(id));
    }
    @Override
    public List<RestaurantResponse> getRestaurants(Long amount, Long page, String sort) {
        List<Restaurant> restaurants = iRestaurantServicePort.getAllRestaurant(amount, page, sort);
        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            restaurantResponses.add(iRestaurantResponseMapper.toRestaurantResponse(restaurants.get(i)));
        }
        return restaurantResponses;
    }
    @Override
    public Restaurant getRestaurantByIdOwner(Long idOwner) {
        return iRestaurantServicePort.getRestaurantByIdOwner(idOwner);
    }
    @Override
    public boolean existByName(String name) {
        return iRestaurantServicePort.existByName(name);
    }
    @Override
    public boolean existById(Long id) {
        return iRestaurantServicePort.existById(id);
    }
    @Override
    public boolean existByIdOwner(Long idOwner) {
        return iRestaurantServicePort.existByIdOwner(idOwner);
    }
}
