package com.powerup.user.domain.api;

import com.powerup.user.domain.model.Role;

import java.util.List;


public interface IRoleServicePort {
    Role getRoleById(Long id);
    Role getRoleByName(String name);
}
