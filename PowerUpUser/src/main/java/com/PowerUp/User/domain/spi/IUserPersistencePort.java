package com.PowerUp.User.domain.spi;

import com.PowerUp.User.domain.model.User;


public interface IUserPersistencePort {
    void saveUser(User user);
    User getUser(Long id);
}
