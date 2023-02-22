package com.powerup.user.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private Long idRole;
    private String idDocument;
}
