package com.powerup.user.application.handler.impl;

import com.powerup.user.application.dto.UserRequest;
import com.powerup.user.application.dto.UserResponse;
import com.powerup.user.application.handler.IUserHandler;
import com.powerup.user.application.mapper.IUserRequestMapper;
import com.powerup.user.application.mapper.IUserResponseMapper;
import com.powerup.user.domain.api.IUserServicePort;
import com.powerup.user.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Transactional
public class UserHandler implements IUserHandler {
    private final IUserServicePort iUserServicePort;
    private  final IUserRequestMapper iUserRequestMapper;
    private final IUserResponseMapper iUserResponseMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserHandler(IUserServicePort iUserServicePort, IUserRequestMapper iUserRequestMapper, IUserResponseMapper iUserResponseMapper) {
        this.iUserServicePort = iUserServicePort;
        this.iUserRequestMapper = iUserRequestMapper;
        this.iUserResponseMapper = iUserResponseMapper;
    }

    @Override
    public void saveUser(UserRequest userRequest, Long idRol) {
        User user = iUserRequestMapper.toUser(userRequest);
        user.setId(-1L);
        user.setIdRole(idRol);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        iUserServicePort.saveUser(user);
    }

    @Override
    public UserResponse getUser(Long id) {
        User user = iUserServicePort.getUser(id);
        return iUserResponseMapper.toUserResponse(user);
    }

}
