package com.powerup.user.domain.api;

import com.powerup.user.domain.model.User;


public interface IUserServicePort {
    void saveUser(User user);
    User getUser(Long id);
}
