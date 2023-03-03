package com.powerup.user.infraestructure.configuration.RestauranteClient;

import com.powerup.user.application.dto.PlateRequest;
import com.powerup.user.application.dto.PlateUpdatingRequest;
import com.powerup.user.application.dto.RestaurantRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="plazoleta-service",url = "http://localhost:8080/" ) //configuration = CustomFeignConfig.class)
public interface RestaurantClient {
    @RequestMapping(method = RequestMethod.POST, value = "restaurants/createRestaurant")
    public ResponseEntity<Void> saveRestaurant(@RequestBody RestaurantRequest restaurantRequest);
    @RequestMapping(method = RequestMethod.POST, value = "plates/createPlate")
    public ResponseEntity<Void> savePlate(@RequestBody PlateRequest plateRequest);
    @RequestMapping(method = RequestMethod.PUT, value = "plates/putPlate")
    public ResponseEntity<Void> editPlate(@RequestBody PlateUpdatingRequest plateUpdatingRequest);

}
