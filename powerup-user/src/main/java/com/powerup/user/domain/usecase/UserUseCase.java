package com.powerup.user.domain.usecase;

import com.powerup.user.domain.api.IRoleServicePort;
import com.powerup.user.domain.api.IUserServicePort;
import com.powerup.user.domain.exception.NoDataFoundException;
import com.powerup.user.domain.exception.UserAlreadyExistsException;
import com.powerup.user.domain.model.User;
import com.powerup.user.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRoleServicePort roleServicePort;
    public UserUseCase(IUserPersistencePort userPersistencePort, IRoleServicePort roleServicePort) {
        this.userPersistencePort = userPersistencePort;
        this.roleServicePort = roleServicePort;
    }
    @Override
    public User saveUser(User user, Long idRol) {
        if(existsByEmail(user.getEmail())) throw new UserAlreadyExistsException();
        user.setRole(roleServicePort.getRoleById(idRol));
        user.setId(-1L);
        return userPersistencePort.saveUser(user);
    }
    @Override
    public User getUser(Long id) {
        if(!existsByID(id)) throw new NoDataFoundException();
        return userPersistencePort.getUser(id);
    }
    @Override
    public User getUserByEmail(String email) {
        if(!existsByEmail(email)) throw new NoDataFoundException();
        return userPersistencePort.getUserByEmail(email);
    }
    @Override
    public boolean existsByEmail(String email) {
        return userPersistencePort.existsByEmail(email);
    }
    @Override
    public boolean existsByID(Long id) {
        return userPersistencePort.existsByID(id);
    }
}
