package com.powerup.user.application;

import com.powerup.user.application.dto.UserRequest;
import com.powerup.user.application.dto.UserResponse;
import com.powerup.user.domain.model.Role;
import com.powerup.user.domain.model.User;

public class SaveHandlerDataTest {

    public static User obtainUser(){
        User user = new User(
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

        return user;
    }

    public static UserRequest obtainUserRequest(){
        UserRequest userRequest = new UserRequest();

        userRequest.setName("user");
        userRequest.setLastName("1");
        userRequest.setIdDocument("1025841144");
        userRequest.setPhone("3013542310");
        userRequest.setEmail("user1@gmail.com");
        userRequest.setPassword("12345");

        return userRequest;
    }

    public static UserResponse obtainUserResponse(){
        UserResponse userResponse = new UserResponse();

        userResponse.setName("user");
        userResponse.setLastName("1");
        userResponse.setPhone("3013542310");
        userResponse.setEmail("user1@gmail.com");
        userResponse.setRole(new Role(2L, "ROLE_PROPRIETARY", "Proprietary"));

        return userResponse;
    }

}
