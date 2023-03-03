package com.powerup.user.infraestructure.input.rest;

import com.powerup.user.application.dto.PlateRequest;
import com.powerup.user.application.dto.PlateUpdatingRequest;
import com.powerup.user.application.dto.RestaurantRequest;
import com.powerup.user.infraestructure.configuration.RestauranteClient.RestaurantClient;
import com.powerup.user.infraestructure.out.jpa.repository.IUserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/square")
@RequiredArgsConstructor
public class SquareRestController {
    private final RestaurantClient restaurantClient;
    private final IUserRepository userRepository;
    @Operation(summary = "Create restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "restaurant already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/admin/restaurant")
    public ResponseEntity<Void> saveRestaurant(@Validated @RequestBody RestaurantRequest restaurantRequest){

        if(userRepository.findById(restaurantRequest.getIdOwner()).get().getRole().getId() == 1) {
            restaurantClient.saveRestaurant(restaurantRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @Operation(summary = "Create new plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "plate created", content = @Content),
            @ApiResponse(responseCode = "409", description = "plate already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/owner/plate")
    public ResponseEntity<RestaurantRequest> savePlate(@Validated @RequestBody PlateRequest plateRequest){
        plateRequest.setIdRestaurant(userRepository.findByEmail(userLoginApplication()).get().getId());
        restaurantClient.savePlate(plateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Modify plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "plate modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "plate doesn't modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PutMapping("/owner/putPlate")
    public ResponseEntity<Void> editPlate(@Validated @RequestBody PlateUpdatingRequest plateUpdatingRequest){
        plateUpdatingRequest.setIdOwner(userRepository.findByEmail(userLoginApplication()).get().getId());
        restaurantClient.editPlate(plateUpdatingRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public static String userLoginApplication() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        return userDetails.getUsername();
    }
}
