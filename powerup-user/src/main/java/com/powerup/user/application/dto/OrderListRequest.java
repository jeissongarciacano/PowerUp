package com.powerup.user.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class OrderListRequest {
    private Long amount;
    private Long page;
    @NotBlank
    private String sort;
    private Long idEmployee;
    @NotBlank
    private String state;
}
