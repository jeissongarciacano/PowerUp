package com.powerup.square.application;

import com.powerup.square.application.dto.plate.ActivatePlateRequest;
import com.powerup.square.application.dto.order.OrderPlateResponse;
import com.powerup.square.application.dto.plate.PlateRequest;
import com.powerup.square.application.dto.plate.PlateUpdatingRequest;
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
                        1L,
                        "Angus Hamburguers",
                        "Street 25",
                        1L,
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
        plateRequest.setUrlImage("www.hamburger.com/asdas.png");

        return plateRequest;
    }

    public static PlateUpdatingRequest obtainPlateUpdatingRequest(){
        PlateUpdatingRequest plateUpdatingRequest = new PlateUpdatingRequest();

        plateUpdatingRequest.setId(100L);
        plateUpdatingRequest.setDescription("Pizza with loots of cheese");
        plateUpdatingRequest.setPrice(15L);
        plateUpdatingRequest.setIdOwner(1L);

        return plateUpdatingRequest;

    }

    public static ActivatePlateRequest obtainActivatePlateRequest() {
        ActivatePlateRequest activatePlateRequest = new ActivatePlateRequest();
        activatePlateRequest.setId(2L);
        activatePlateRequest.setIdOwner(1L);
        return activatePlateRequest;
    }

    public static OrderPlateResponse obtainPlateResponse() {
        OrderPlateResponse orderPlateResponse = new OrderPlateResponse();
        orderPlateResponse.setAmount(1L);
        orderPlateResponse.setCategory(new Category(1L,"pasta", "pastas"));
        orderPlateResponse.setDescription("pastas");
        orderPlateResponse.setName("pastas");
        orderPlateResponse.setId(1L);
        orderPlateResponse.setUrlImage("www.google.com");
        return orderPlateResponse;
    }
}
