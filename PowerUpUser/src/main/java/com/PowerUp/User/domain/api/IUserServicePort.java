package com.PowerUp.User.domain.api;

import com.PowerUp.User.domain.model.User;


public interface IUserServicePort {
    void saveUser(User user);
    User getUser(Long id);
}
