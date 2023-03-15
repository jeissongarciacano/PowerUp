package com.powerup.square.infraestructure.configuration;

import com.powerup.square.application.dto.user.UserResponse;
import com.powerup.square.domain.api.*;
import com.powerup.square.domain.spi.*;
import com.powerup.square.domain.usecase.*;
import com.powerup.square.infraestructure.configuration.security.auth.DetailsUser;
import com.powerup.square.infraestructure.configuration.security.auth.IUserDetailsMapper;
import com.powerup.square.infraestructure.configuration.userclient.UserClient;
import com.powerup.square.infraestructure.out.jpa.adapter.*;
import com.powerup.square.infraestructure.out.jpa.mapper.*;
import com.powerup.square.infraestructure.out.jpa.repository.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.okhttp.OkHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

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
    private final UserClient userClient;
    private final IUserDetailsMapper userDetailsMapper;

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
    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryMapper);
    }
    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
    @Bean
    public UserDetailsService userDetailsService() {

        return username -> optionalDetailsUser(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    private Optional<DetailsUser> optionalDetailsUser(String username) {
        UserResponse userResponse = userClient.getUserByEmail(username);
        DetailsUser user = userDetailsMapper.toUser(userResponse);
        user.setRole(userResponse.getRole().getName());
        return Optional.of(user);
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public TwilioClient twilioClient(){
        return new TwilioClient();
    }
}
