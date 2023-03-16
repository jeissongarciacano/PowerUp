package com.powerup.square.application.dto.plate;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class PlateRequest {

    @NotBlank
    private String name;
    private Long idCategory;
    @NotBlank
    private String description;
    @Min(0L)
    private Long price;
    private Long idOwner;
    @NotBlank
    @URL(message = "must be a url")
    private String urlImage;

}
