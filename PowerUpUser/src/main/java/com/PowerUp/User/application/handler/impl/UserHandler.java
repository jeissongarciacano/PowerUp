package com.PowerUp.User.application.handler.impl;

import com.PowerUp.User.application.dto.UserRequest;
import com.PowerUp.User.application.dto.UserResponse;
import com.PowerUp.User.application.handler.IUserHandler;
import com.PowerUp.User.application.mapper.IUserRequestMapper;
import com.PowerUp.User.application.mapper.IUserResponseMapper;
import com.PowerUp.User.domain.api.IUserServicePort;
import com.PowerUp.User.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserHandler implements IUserHandler {
    private final IUserServicePort iUserServicePort;
    private  final IUserRequestMapper iUserRequestMapper;
    private final IUserResponseMapper iUserResponseMapper;


    public UserHandler(IUserServicePort iUserServicePort, IUserRequestMapper iUserRequestMapper, IUserResponseMapper iUserResponseMapper) {
        this.iUserServicePort = iUserServicePort;
        this.iUserRequestMapper = iUserRequestMapper;
        this.iUserResponseMapper = iUserResponseMapper;
    }

    @Override
    public void saveUser(UserRequest userRequest) {
        iUserServicePort.saveUser(iUserRequestMapper.toUser(userRequest));
    }

    @Override
    public UserResponse getUser(Long id) {
        User user = iUserServicePort.getUser(id);
        return iUserResponseMapper.toUserResponse(user);
    }

}
