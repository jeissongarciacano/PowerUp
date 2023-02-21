package com.PowerUp.User.infraestructure.configuration;

import com.PowerUp.User.domain.api.IUserServicePort;
import com.PowerUp.User.domain.spi.IUserPersistencePort;
import com.PowerUp.User.domain.usecase.UserUseCase;
import com.PowerUp.User.infraestructure.out.jpa.adapter.UserJpaAdapter;
import com.PowerUp.User.infraestructure.out.jpa.mapper.IRoleMapper;
import com.PowerUp.User.infraestructure.out.jpa.mapper.IUserMapper;
import com.PowerUp.User.infraestructure.out.jpa.repository.IRoleRepository;
import com.PowerUp.User.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository,userMapper);
    }
    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort());
    }

}
