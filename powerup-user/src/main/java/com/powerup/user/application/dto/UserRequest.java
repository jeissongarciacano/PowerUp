package com.powerup.user.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserRequest {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String idDocument;
    @NotBlank
    @Pattern(regexp = "^(\\+57)?'3'\\d{9}$")
    private String phone;
    @NotEmpty
    @Email
    private String email;
    @NotBlank
    private String password;
}
