package com.powerup.square.infraestructure.input.rest;

import com.powerup.square.application.dto.RestaurantRequest;
import com.powerup.square.application.handler.IRestaurantHandler;
import com.powerup.square.infraestructure.out.jpa.entity.RestaurantEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor

public class RestaurantRestControler {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Add a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant already exists", content = @Content)
    })
    @PostMapping("/createRestaurant/")
    public ResponseEntity<Void> saveRestaurantEntity(@RequestBody @Validated RestaurantRequest restaurantRequest){
        restaurantHandler.saveRestaurant(restaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Get restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant already exists", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<RestaurantEntity>> getAllRestaurant(){
        return null;
    }
    @Operation(summary = "change restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant already exists", content = @Content)
    })
    @PutMapping
    public ResponseEntity<Void> editRestaurant(@RequestBody RestaurantEntity restaurantEntity){
        return null;
    }

}
