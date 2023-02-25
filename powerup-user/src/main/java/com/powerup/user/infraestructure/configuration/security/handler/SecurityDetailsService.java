package com.powerup.user.infraestructure.configuration.security.handler;

import com.powerup.user.domain.exception.ErrorCodesEnum;
import com.powerup.user.domain.model.Role;
import com.powerup.user.domain.usecase.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityDetailsService implements UserDetailsService {
    @Autowired
    private UserUseCase userUseCase;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws SecurityException{
        com.powerup.user.domain.model.User  user = userUseCase.getUserByEmail(username);

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        Role role = user.getRole();

        authorities.add(new SimpleGrantedAuthority(role.getName()));

        if(authorities.isEmpty()) {
            throw new SecurityException(String.valueOf(ErrorCodesEnum.USER_NO_PERMISSIONS));
        }

        return new User(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
    }
}
