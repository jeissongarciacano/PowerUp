package com.powerup.user.application.handler;

import com.powerup.user.application.dto.UserRequest;
import com.powerup.user.application.dto.UserResponse;

public interface IUserHandler {
    void saveUser(UserRequest userRequest, Long idRol);
    UserResponse getUser(Long id);
}
