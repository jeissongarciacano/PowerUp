package com.PowerUp.Square.infraestructure.configuration;

import com.PowerUp.Square.domain.api.IRestaurantServicePort;
import com.PowerUp.Square.domain.spi.IRestaurantPersistencePort;
import com.PowerUp.Square.domain.usecase.RestaurantUseCase;
import com.PowerUp.Square.infraestructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.PowerUp.Square.infraestructure.out.jpa.mapper.ICategoryMapper;
import com.PowerUp.Square.infraestructure.out.jpa.mapper.IOrderMapper;
import com.PowerUp.Square.infraestructure.out.jpa.mapper.IPlateMapper;
import com.PowerUp.Square.infraestructure.out.jpa.mapper.IRestaurantMapper;
import com.PowerUp.Square.infraestructure.out.jpa.repository.ICategoryRepository;
import com.PowerUp.Square.infraestructure.out.jpa.repository.IOrderRepository;
import com.PowerUp.Square.infraestructure.out.jpa.repository.IPlateRepository;
import com.PowerUp.Square.infraestructure.out.jpa.repository.IRestaurantRepository;
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
