package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.RestaurantListRequest;
import com.powerup.square.application.dto.RestaurantRequest;
import com.powerup.square.application.dto.RestaurantResponse;
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
    public void saveRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = iRestaurantRequestMapper.toRestaurant(restaurantRequest);
        iRestaurantServicePort.saveRestaurant(restaurant);
    }

    @Override
    public RestaurantResponse getRestaurant(Long id) {
        Restaurant restaurant = iRestaurantServicePort.getRestaurant(id);
        return iRestaurantResponseMapper.toRestaurantResponse(restaurant);
    }

    @Override
    public List<RestaurantResponse> getRestaurants(RestaurantListRequest restaurantListRequest) {
        List<Restaurant> restaurants = iRestaurantServicePort.getAllRestaurant(restaurantListRequest);
        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            restaurantResponses.add(iRestaurantResponseMapper.toRestaurantResponse(restaurants.get(i)));
        }
        return restaurantResponses;
    }

}
