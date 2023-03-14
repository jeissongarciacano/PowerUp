package com.powerup.user.domain.api;

import com.powerup.user.domain.model.User;

import java.util.List;


public interface IUserServicePort {
    User saveUser(User user, Long idRol);
    User getUser(Long id);
    User getUserByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByID(Long id);
}
