package com.powerup.square.application;

import com.powerup.square.application.dto.restaurant.RestaurantRequest;
import com.powerup.square.application.handler.impl.RestaurantHandler;
import com.powerup.square.application.mapper.IRestaurantRequestMapper;
import com.powerup.square.application.mapper.IRestaurantResponseMapper;
import com.powerup.square.domain.api.IRestaurantServicePort;
import com.powerup.square.domain.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RestaurantHandlerTest {

    @InjectMocks
    RestaurantHandler restaurantHandler;

    @Mock
    IRestaurantServicePort iRestaurantServicePort;

    @Mock
    IRestaurantRequestMapper iRestaurantRequestMapper;

    @Mock
    IRestaurantResponseMapper iRestaurantResponseMapper;

    @Test
    void saveRestaurant() {

        Restaurant restaurant = SaveRestaurantHandlerDataTest.obtainRestaurant();
        RestaurantRequest restaurantRequest = SaveRestaurantHandlerDataTest.obtainRestaurantRequest();


        when(iRestaurantRequestMapper.toRestaurant(restaurantRequest)).thenReturn(restaurant);
        restaurantHandler.saveRestaurant(restaurantRequest);

        verify(iRestaurantServicePort).saveRestaurant(restaurant);

    }

    @Test
    void getRestaurant() {

        Restaurant restaurant = SaveRestaurantHandlerDataTest.obtainRestaurant();

        when(iRestaurantServicePort.getRestaurant(anyLong())).thenReturn(restaurant);
        restaurantHandler.getRestaurant(anyLong());

        verify(iRestaurantResponseMapper).toRestaurantResponse(restaurant);

    }
    @Test
    void getRestaurants(){
        List<Restaurant> restaurantList = SaveRestaurantHandlerDataTest.obtainRestaurants();
        when(iRestaurantServicePort.getAllRestaurant(anyLong(),anyLong(), anyString())).thenReturn(restaurantList);
        assertEquals(restaurantHandler.getRestaurants(anyLong(), anyLong(), anyString()),restaurantList );
    }
}