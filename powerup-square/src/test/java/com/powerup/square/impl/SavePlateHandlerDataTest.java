package com.powerup.square.impl;

import com.powerup.square.application.dto.PlateRequest;
import com.powerup.square.application.dto.PlateUpdatingRequest;
import com.powerup.square.domain.model.Category;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.model.Restaurant;

public class SavePlateHandlerDataTest {

    public static Plate obtainPlate(){
        Plate plate = new Plate(
                2L,
                "Mexican Hamburguer",
                new Category(
                        1L,
                        "Hamburger",
                        "Has 2 breads, tomato, lettuce, bacon and mozzarella cheese"
                ),
                "Have nachos, sour cream, guacamole and pico de gallo",
                15L,
                new Restaurant(
                        100L,
                        "Angus Hamburguers",
                        "Street 25",
                        10L,
                        "3013218520",
                        "www.logo.es",
                        "ASD-121854-YU"
                ),
                "www.hamburger.com/asdas.png",
                true
        );

        return plate;
    }

    public static PlateRequest obtainPlateRequest(){
        PlateRequest plateRequest = new PlateRequest();

        plateRequest.setName("Mexican Hamburguer");
        plateRequest.setIdCategory(1L);
        plateRequest.setDescription("Have nachos, sour cream, guacamole and pico de gallo");
        plateRequest.setPrice(15L);
        plateRequest.setIdRestaurant(100L);
        plateRequest.setUrlImage("www.hamburger.com/asdas.png");

        return plateRequest;
    }

    public static PlateUpdatingRequest obtainPlateUpdatingRequest(){
        PlateUpdatingRequest plateUpdatingRequest = new PlateUpdatingRequest();

        plateUpdatingRequest.setId(100L);
        plateUpdatingRequest.setDescription("Pizza with loots of cheese");
        plateUpdatingRequest.setPrice(15L);
        plateUpdatingRequest.setIdOwner(2L);

        return plateUpdatingRequest;

    }

}
