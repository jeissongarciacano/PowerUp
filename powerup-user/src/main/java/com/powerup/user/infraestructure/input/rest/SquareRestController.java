package com.powerup.user.infraestructure.input.rest;

import com.powerup.user.application.dto.PlateRequest;
import com.powerup.user.application.dto.PlateUpdatingRequest;
import com.powerup.user.application.dto.RestaurantRequest;
import com.powerup.user.infraestructure.configuration.RestauranteClient.RestaurantClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/square")
@RequiredArgsConstructor
public class SquareRestController {
    private final RestaurantClient restaurantClient;
    @Operation(summary = "Create restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "restaurant already exists", content = @Content)
    })
    @PostMapping("/Administrador/restaurant")
    public ResponseEntity<Void> saveRestaurant(@Validated @RequestBody RestaurantRequest restaurantRequest){
        restaurantClient.saveRestaurant(restaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Create new plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "plate created", content = @Content),
            @ApiResponse(responseCode = "409", description = "plate already exists", content = @Content)
    })
    @PostMapping("/Propietario/plate")
    public ResponseEntity<RestaurantRequest> savePlate(@Validated @RequestBody PlateRequest plateRequest){
        restaurantClient.savePlate(plateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Modify plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "plate created", content = @Content),
            @ApiResponse(responseCode = "409", description = "plate already exists", content = @Content)
    })
    @PutMapping("/Propietario/putPlate")
    public ResponseEntity<Void> editPlate(@Validated @RequestBody PlateUpdatingRequest plateUpdatingRequest){
        restaurantClient.editPlate(plateUpdatingRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
