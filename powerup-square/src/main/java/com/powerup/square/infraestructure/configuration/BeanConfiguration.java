package com.powerup.square.infraestructure.configuration;

import com.powerup.square.domain.api.*;
import com.powerup.square.domain.spi.*;
import com.powerup.square.domain.usecase.*;
import com.powerup.square.infraestructure.out.jpa.adapter.*;
import com.powerup.square.infraestructure.out.jpa.mapper.*;
import com.powerup.square.infraestructure.out.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantMapper restaurantMapper;
    private final IPlateRepository plateRepository;
    private final IPlateMapper plateMapper;
    private final ICategoryMapper categoryMapper;
    private final ICategoryRepository categoryRepository;
    private final IEmployeeRepository employeeRepository;
    private final IEmployeeMapper employeeMapper;
    private final IOrderMapper orderMapper;
    private final IOrderRepository orderRepository;
    private final IOrderPlatesMapper orderPlatesMapper;
    private final IOrderPlatesRepository orderPlatesRepository;

    @Bean
    @Primary
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository,restaurantMapper);
    }
    @Bean
    public IRestaurantServicePort restaurantServicePort(){

        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public IPlatePersistencePort platePersistencePort(){
        return new PlateJpaAdapter(plateRepository, plateMapper);
    }

    @Bean
    public IPlateServicePort plateServicePort() { return new PlateUseCase(platePersistencePort());}
    @Bean
    public IEmployeePersistencePort employeePersistencePort(){
        return new EmployeeJpaAdapter(employeeRepository, employeeMapper);
    }
    @Bean
    public IEmployeeServicePort employeeServicePort(){
        return new EmployeeUseCase(employeePersistencePort());
    }
    @Bean
    public IOrderPlatesPersistencePort orderPlatesPersistencePort(){
        return new OrderPlatesJpaAdapter(orderPlatesRepository, orderPlatesMapper, orderMapper);
    }
    @Bean
    public IOrderPlatesServicePort orderPlatesServicePort(){
        return new OrderPlatesUseCase(orderPlatesPersistencePort());
    }
    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter(orderRepository, orderMapper,employeeRepository, restaurantRepository);
    }
    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistencePort());
    }


}
