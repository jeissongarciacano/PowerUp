package com.powerup.user.domain.spi;

import com.powerup.user.domain.model.User;


public interface IUserPersistencePort {
    void saveUser(User user);
    User getUser(Long id);
}
