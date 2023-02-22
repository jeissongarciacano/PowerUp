package com.powerup.user.domain.usecase;

import com.powerup.user.domain.api.IUserServicePort;
import com.powerup.user.domain.model.User;
import com.powerup.user.domain.spi.IUserPersistencePort;
public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public void saveUser(User user) {
        userPersistencePort.saveUser(user);
    }
    @Override
    public User getUser(Long id) {
        return userPersistencePort.getUser(id);
    }
}
