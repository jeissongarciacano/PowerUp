package com.powerup.square.application.dto.user;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EmployeeRequest {
    private Long idUser;
    private Long idOwner;
    private String field;

}
