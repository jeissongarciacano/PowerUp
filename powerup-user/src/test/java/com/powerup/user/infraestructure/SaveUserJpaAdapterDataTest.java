package com.powerup.user.infraestructure;

import com.powerup.user.domain.model.Role;
import com.powerup.user.domain.model.User;
import com.powerup.user.infraestructure.out.jpa.entity.RoleEntity;
import com.powerup.user.infraestructure.out.jpa.entity.UserEntity;

public class SaveUserJpaAdapterDataTest {

    public static User obtainUser() {

        return new User(
                2L,
                "User",
                "1",
                "3013542310",
                "User1@gmail.com",
                "12345",
                new Role(
                        2L,
                        "ROLE_PROPRIETARY",
                        "Proprietary"
                ),
                "1025841144"
        );

    }

    public static UserEntity obtainUserEntity(){
        UserEntity userEntity = new UserEntity();

        userEntity.setId(11L);
        userEntity.setName("Jeisson");
        userEntity.setLastName("Garcia");
        userEntity.setPhone("3013218602");
        userEntity.setEmail("jeisson@gmail.com");
        userEntity.setPassword("12345");
        userEntity.setRole(new RoleEntity(
                2L,
                "ROLE_PROPRIETARY",
                "Proprietary"
        ));
        userEntity.setIdDocument("1025630112");

        return userEntity;
    }

}
