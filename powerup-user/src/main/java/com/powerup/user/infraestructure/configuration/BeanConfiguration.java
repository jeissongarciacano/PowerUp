package com.powerup.user.infraestructure.configuration;

import com.powerup.user.domain.api.IUserServicePort;
import com.powerup.user.domain.spi.IUserPersistencePort;
import com.powerup.user.domain.usecase.UserUseCase;
import com.powerup.user.infraestructure.out.jpa.adapter.RoleJpaAdapter;
import com.powerup.user.infraestructure.out.jpa.adapter.UserJpaAdapter;
import com.powerup.user.infraestructure.out.jpa.mapper.IUserMapper;
import com.powerup.user.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final RoleJpaAdapter roleJpaAdapter;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository,userMapper, roleJpaAdapter);
    }
    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort());
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
