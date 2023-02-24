package com.powerup.square.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RestaurantRequest {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "The field name shouldn't have numbers")
    private String name;
    @NotBlank (message = "The field address is mandatory")
    private String address;
    @NotBlank (message = "The field idOwner is mandatory")
    private String idOwner;

    @NotBlank (message = "The field url is mandatory")
    @Pattern(regexp = "^[+][0-9]*$|^[0-9]*$", message = "The phone must be numeric")
    private String phone;

    @NotBlank (message = "The url field is mandatory")
    private String urlLogo;

    @Pattern(regexp = "^[0-9]*$", message = "The NIT must be numeric")
    @NotBlank (message = "The field NIT is mandatory")
    private String nit;
}
