package com.powerup.user.application.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EmployeeRequest {
    private Long idUser;
    private Long idOwner;
    private String field;
}
