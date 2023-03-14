package com.powerup.user.application.handler;

import com.powerup.user.application.dto.UserRequest;
import com.powerup.user.application.dto.UserResponse;

import java.util.List;

public interface IUserHandler {
    UserResponse saveUser(UserRequest userRequest, Long idRol);
    UserResponse getUser(Long id);
    UserResponse getUserByEmail(String email);
    UserResponse getUserById(Long id);
}
