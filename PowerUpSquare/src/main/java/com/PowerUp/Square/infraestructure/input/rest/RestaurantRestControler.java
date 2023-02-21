package com.PowerUp.Square.infraestructure.input.rest;

import com.PowerUp.Square.application.dto.RestaurantRequest;
import com.PowerUp.Square.application.handler.IRestaurantHandler;
import com.PowerUp.Square.infraestructure.out.jpa.entity.RestaurantEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Restaurant")
@RequiredArgsConstructor

public class RestaurantRestControler {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Add a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveRestaurantEntity(@RequestBody RestaurantRequest restaurantRequest){
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