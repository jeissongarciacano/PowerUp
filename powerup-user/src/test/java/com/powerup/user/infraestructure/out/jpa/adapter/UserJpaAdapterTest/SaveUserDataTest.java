package com.powerup.user.infraestructure.out.jpa.adapter.UserJpaAdapterTest;

import com.powerup.user.domain.model.User;
import com.powerup.user.domain.model.Role;
import com.powerup.user.infraestructure.out.jpa.entity.RoleEntity;
import com.powerup.user.infraestructure.out.jpa.entity.UserEntity;

public class SaveUserDataTest {

    public static User obtainUser(){
        User user = new User(-1L,"Richard","Roe","3013218602","annie24@gmail.com",
                "aa78",obtainRole(),"1017148689");
        return user;
    }

    public static Role obtainRole(){
        Role role = new Role(0L,"Admin","Administer all stuff");
        return role;

    }

    public static UserEntity obtainUserEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("Richard");
        userEntity.setLastName("Roe");
        userEntity.setPhone("3013218602");
        userEntity.setEmail("angiep2008@gmail.com");
        userEntity.setPassword("aa78");
        userEntity.setIdDocument("1017148689");

        return userEntity;
    }

    public static RoleEntity obtainRoleEntity(){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setDescription("Administer all stuff");
        roleEntity.setName("Admin");

        return roleEntity;
    }

}
