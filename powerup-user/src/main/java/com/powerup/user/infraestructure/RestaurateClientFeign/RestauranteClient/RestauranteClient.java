package com.powerup.user.infraestructure.RestaurateClientFeign.RestauranteClient;

import com.powerup.user.application.dto.RestauranteRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="plazoleta-service",url = "http://localhost:8080/restaurants" ) //configuration = CustomFeignConfig.class)
public interface RestauranteClient {
    //@PostMapping("/restaurante")
    @RequestMapping(method = RequestMethod.POST, value = "/createRestaurant/")
    public ResponseEntity<RestauranteRequest> saveRestaurante(@RequestBody RestauranteRequest restauranteRequest);


}
