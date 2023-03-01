package com.powerup.user.infraestructure.configuration.security;

import com.powerup.user.infraestructure.out.jpa.entity.UserEntity;
import com.powerup.user.infraestructure.out.jpa.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final IUserRepository userAuthRepository;
    private final IUserRepository userRepository;


    public UserDetailServiceImpl(IUserRepository userAuthRepository, IUserRepository userRepository) {
        this.userAuthRepository = userAuthRepository;

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            UserEntity user = userAuthRepository.findByEmail(email).get();
            return new UserDetailsImpl(user);

    }

}
