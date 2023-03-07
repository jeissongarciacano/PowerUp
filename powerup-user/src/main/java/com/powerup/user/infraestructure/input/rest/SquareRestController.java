package com.powerup.user.infraestructure.input.rest;

import com.powerup.user.application.dto.*;
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

import java.util.List;

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
    @Operation(summary = "Activate or Disable Plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "plate modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "plate doesn't modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PutMapping("/owner/activatePlate")
    public ResponseEntity<Void> activatePlate(@RequestBody ActivatePlateRequest activatePlateRequest){
        activatePlateRequest.setIdOwner(userRepository.findByEmail(userLoginApplication()).get().getId());
        restaurantClient.activatePlate(activatePlateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(summary = "get All Restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "plate modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "plate doesn't modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/client/getAllRestaurant")
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurant(@Validated @RequestBody RestaurantListRequest restaurantListRequest){
        return restaurantClient.getAllRestaurant(restaurantListRequest);
    }
    @Operation(summary = "get Plates of Restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "plate modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "plate doesn't modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/client/getRestaurantPlates")
    public ResponseEntity<List<RestaurantResponse>> getRestaurantPlates(@RequestBody PlateListRequest plateListRequest){
        return restaurantClient.getRestaurantPlates(plateListRequest);
    }
    @Operation(summary = "make Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "plate modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "plate doesn't modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/client/makeOrder")
    public ResponseEntity<Void> makeOrder(@Validated @RequestBody OrderRequest orderRequest){
        restaurantClient.makeOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(summary = "get All Order By State")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "plate modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "plate doesn't modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @GetMapping("/employee/getAllOrderByState/{state}")
    public ResponseEntity<List<OrderResponse>> getAllOrderByState(@PathVariable String state){
        return restaurantClient.getAllOrderByState(state,userRepository.findByEmail(userLoginApplication()).get().getId());
    }
    @Operation(summary = "take Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "plate modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "plate doesn't modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PutMapping("/employee/takeOrder/{id}")
    public ResponseEntity<Void> takeOrder(@PathVariable Long id){
        restaurantClient.takeOrder(id,userRepository.findByEmail(userLoginApplication()).get().getId() );
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(summary = "Change Order to ready to deliver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "plate modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "plate doesn't modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PutMapping("/employee/readyToDeliver/{id}")
    public ResponseEntity<Void> readyToDeliver(@PathVariable Long id){
        restaurantClient.readyToDeliver(id,userRepository.findByEmail(userLoginApplication()).get().getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(summary = "Change Order to ready to deliver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "plate modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "plate doesn't modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PutMapping("/employee/deliverOrder/{id}")
    public ResponseEntity<Void> deliverOrder(@PathVariable Long id){
        restaurantClient.deliverOrder(id,userRepository.findByEmail(userLoginApplication()).get().getId() );
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(summary = "cancel Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "plate modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "plate doesn't modify", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PutMapping("/client/cancelOrder/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id){
        restaurantClient.cancelOrder(id,userRepository.findByEmail(userLoginApplication()).get().getId() );
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
