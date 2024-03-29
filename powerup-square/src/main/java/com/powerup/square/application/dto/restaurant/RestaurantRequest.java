package com.powerup.square.application.dto.restaurant;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RestaurantRequest {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+", message = "The field name shouldn't have numbers")
    private String name;
    @NotBlank (message = "The field address is mandatory")
    private String address;
    private Long idOwner;
    @NotBlank (message = "The field url is mandatory")
    @Pattern(regexp = "^(\\+57)?3\\d{9}$", message = "The phone must be numeric")
    private String phone;

    @NotBlank (message = "The url field is mandatory")
    @URL(message = "must be a url")
    private String urlLogo;

    @Pattern(regexp = "^[0-9]*$", message = "The NIT must be numeric")
    @NotBlank (message = "The field NIT is mandatory")
    private String nit;
}
