package com.PowerUp.User.domain.usecase;

import com.PowerUp.User.domain.api.IUserServicePort;
import com.PowerUp.User.domain.model.User;
import com.PowerUp.User.domain.spi.IUserPersistencePort;
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
