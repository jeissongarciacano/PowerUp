package com.PowerUp.User.application.handler;

import com.PowerUp.User.application.dto.UserRequest;
import com.PowerUp.User.application.dto.UserResponse;

public interface IUserHandler {
    void saveUser(UserRequest userRequest);
    UserResponse getUser(Long id);
}
