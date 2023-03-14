package com.powerup.user.application.handler.impl;

import com.powerup.user.application.dto.UserRequest;
import com.powerup.user.application.dto.UserResponse;
import com.powerup.user.application.handler.IUserHandler;
import com.powerup.user.application.mapper.IUserRequestMapper;
import com.powerup.user.application.mapper.IUserResponseMapper;
import com.powerup.user.domain.api.IUserServicePort;
import com.powerup.user.domain.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserHandler implements IUserHandler {
    private final IUserServicePort iUserServicePort;
    private  final IUserRequestMapper iUserRequestMapper;
    private final IUserResponseMapper iUserResponseMapper;
    private final PasswordEncoder passwordEncoder;

    public UserHandler(IUserServicePort iUserServicePort, IUserRequestMapper iUserRequestMapper, IUserResponseMapper iUserResponseMapper, PasswordEncoder passwordEncoder) {
        this.iUserServicePort = iUserServicePort;
        this.iUserRequestMapper = iUserRequestMapper;
        this.iUserResponseMapper = iUserResponseMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserResponse saveUser(UserRequest userRequest, Long idRol) {
        User user = iUserRequestMapper.toUser(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return iUserResponseMapper.toUserResponse(iUserServicePort.saveUser(user, idRol));
    }

    @Override
    public UserResponse getUser(Long id) {
        User user = iUserServicePort.getUser(id);
        return iUserResponseMapper.toUserResponse(user);
    }
    @Override
    public UserResponse getUserByEmail(String email) {
        return iUserResponseMapper.toUserResponse(iUserServicePort.getUserByEmail(email));
    }

    @Override
    public UserResponse getUserById(Long id) {
        return iUserResponseMapper.toUserResponse(iUserServicePort.getUser(id));
    }
}
