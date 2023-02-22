package com.PowerUp.Square.application.handler.impl;

import com.PowerUp.Square.application.dto.RestaurantRequest;
import com.PowerUp.Square.application.dto.RestaurantResponse;
import com.PowerUp.Square.application.handler.IRestaurantHandler;
import com.PowerUp.Square.application.mapper.IRestaurantRequestMapper;
import com.PowerUp.Square.application.mapper.IRestaurantResponseMapper;
import com.PowerUp.Square.domain.api.IRestaurantServicePort;
import com.PowerUp.Square.domain.model.Restaurant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        iRestaurantServicePort.saveRestaurant(iRestaurantRequestMapper.toRestaurant(restaurantRequest));
    }

    @Override
    public RestaurantResponse getRestaurant(Long id) {
        Restaurant restaurant = iRestaurantServicePort.getRestaurant(id);
        return iRestaurantResponseMapper.toRestaurantResponse(restaurant);
    }

}
