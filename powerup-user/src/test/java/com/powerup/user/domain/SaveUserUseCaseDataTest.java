package com.powerup.user.domain;

import com.powerup.user.domain.model.Role;
import com.powerup.user.domain.model.User;

public class SaveUserUseCaseDataTest {

    public static User obtainUser(){

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



}
