package com.powerup.square.infraestructure.input.rest;

import com.powerup.square.application.dto.restaurant.RestaurantRequest;
import com.powerup.square.application.dto.restaurant.RestaurantResponse;
import com.powerup.square.application.handler.IRestaurantHandler;
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
public class RestaurantRestController {
    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Add a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/admin/create_restaurant")
    public ResponseEntity<RestaurantResponse> saveRestaurantEntity(@Validated @RequestBody RestaurantRequest restaurantRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantHandler.saveRestaurant(restaurantRequest));
    }
    @Operation(summary = "Get restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Restaurants found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Restaurants don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @GetMapping("/client/get_restaurant/{amount}/{page}/{sort}")
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurant(@PathVariable Long amount, @PathVariable Long page,@PathVariable String sort){
        if(amount <= 0L || page < 0 || sort.isEmpty() || sort.isBlank()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.FOUND).body(restaurantHandler.getRestaurants(amount, page, sort));
    }
}
