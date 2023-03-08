package com.powerup.square.domain;

import com.powerup.square.domain.model.Restaurant;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import com.powerup.square.domain.usecase.RestaurantUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class RestaurantUseCaseTest {

    @InjectMocks
    RestaurantUseCase restaurantUseCase;

    @Mock
    IRestaurantPersistencePort iRestaurantPersistencePort;

    @Test
    void saveRestaurant() {
        Restaurant restaurant = SaveRestaurantUseCaseDataTest.obtainRestaurant();

        restaurantUseCase.saveRestaurant(restaurant);

        verify(iRestaurantPersistencePort).saveRestaurant(restaurant);
    }

    @Test
    void getAllRestaurant() {
        //restaurantUseCase.getAllRestaurant();

        //verify(iRestaurantPersistencePort).getAllRestaurant();
    }

//    @Test
//    void getRestaurant() {
//        restaurantUseCase.getRestaurant(anyLong());
//
//        verify(iRestaurantPersistencePort).getRestaurant(anyLong());
//    }

//    @Test
//    void getRestaurantByIdOwner() {
//        restaurantUseCase.getRestaurantByIdOwner(1L);
//
//        verify(iRestaurantPersistencePort).getRestaurant(1L);
//    }

    @Test
    void existByName() {
        restaurantUseCase.existByName(any());

        verify(iRestaurantPersistencePort).existByName(any());
    }

    @Test
    void existById() {
        restaurantUseCase.existById(anyLong());

        verify(iRestaurantPersistencePort).existById(anyLong());
    }

    @Test
    void existByIdOwner() {
        restaurantUseCase.existByIdOwner(anyLong());

        verify(iRestaurantPersistencePort).existByIdOwner(anyLong());
    }
}