package com.powerup.user.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private Long idRole;
    private String idDocument;
}
