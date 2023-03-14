package com.powerup.user.domain.spi;

import com.powerup.user.domain.model.Role;

import java.util.List;


public interface IRolePersistencePort {
    Role getRoleById(Long id);
    Role getRoleByName(String name);
}
