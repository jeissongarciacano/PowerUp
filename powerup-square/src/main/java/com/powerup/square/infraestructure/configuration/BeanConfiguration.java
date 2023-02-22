package com.powerup.square.infraestructure.configuration;

import com.powerup.square.domain.api.IRestaurantServicePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import com.powerup.square.domain.usecase.RestaurantUseCase;
import com.powerup.square.infraestructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.powerup.square.infraestructure.out.jpa.mapper.ICategoryMapper;
import com.powerup.square.infraestructure.out.jpa.mapper.IOrderMapper;
import com.powerup.square.infraestructure.out.jpa.mapper.IPlateMapper;
import com.powerup.square.infraestructure.out.jpa.mapper.IRestaurantMapper;
import com.powerup.square.infraestructure.out.jpa.repository.ICategoryRepository;
import com.powerup.square.infraestructure.out.jpa.repository.IOrderRepository;
import com.powerup.square.infraestructure.out.jpa.repository.IPlateRepository;
import com.powerup.square.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantMapper restaurantMapper;
    private final IPlateRepository plateRepository;
    private final IPlateMapper plateMapper;
    private final IOrderRepository orderRepository;
    private  final IOrderMapper orderMapper;
    private final ICategoryRepository categoryRepository;
    private final ICategoryMapper categoryMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository,restaurantMapper);
    }
    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort());
    }

}
