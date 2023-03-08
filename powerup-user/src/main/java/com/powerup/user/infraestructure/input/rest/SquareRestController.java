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
            @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/admin/restaurant")
    public ResponseEntity<Void> saveRestaurant(@Validated @RequestBody RestaurantRequest restaurantRequest){

        if(userRepository.findById(restaurantRequest.getIdOwner()).get().getRole().getId() == 1)
            return restaurantClient.saveRestaurant(restaurantRequest);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @Operation(summary = "Create new plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plate created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/owner/plate")
    public ResponseEntity<Void> savePlate(@Validated @RequestBody PlateRequest plateRequest){
        plateRequest.setIdRestaurant(userRepository.findByEmail(userLoginApplication()).get().getId());
        return restaurantClient.savePlate(plateRequest);
    }
    @Operation(summary = "Modify plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate modified", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/owner/putPlate")
    public ResponseEntity<Void> editPlate(@Validated @RequestBody PlateUpdatingRequest plateUpdatingRequest){
        plateUpdatingRequest.setIdOwner(userRepository.findByEmail(userLoginApplication()).get().getId());
        return restaurantClient.editPlate(plateUpdatingRequest);
    }
    @Operation(summary = "Activate or Disable Plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate disable or active", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/owner/activatePlate")
    public ResponseEntity<Void> activatePlate(@RequestBody ActivatePlateRequest activatePlateRequest){
        activatePlateRequest.setIdOwner(userRepository.findByEmail(userLoginApplication()).get().getId());
        return restaurantClient.activatePlate(activatePlateRequest);
    }
    @Operation(summary = "get All Restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Restaurants found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Restaurants don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/client/getAllRestaurant")
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurant(@Validated @RequestBody RestaurantListRequest restaurantListRequest){
        return restaurantClient.getAllRestaurant(restaurantListRequest);
    }
    @Operation(summary = "get Plates of Restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Plates found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Plates don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/client/getRestaurantPlates")
    public ResponseEntity<List<RestaurantResponse>> getRestaurantPlates(@RequestBody PlateListRequest plateListRequest){
        return restaurantClient.getRestaurantPlates(plateListRequest);
    }
    @Operation(summary = "make Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/client/makeOrder")
    public ResponseEntity<Void> makeOrder(@Validated @RequestBody OrderRequest orderRequest){
        return restaurantClient.makeOrder(orderRequest);
    }
    @Operation(summary = "get All Order By State")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Orders found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Orders don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @GetMapping("/employee/getAllOrderByState/{state}")
    public ResponseEntity<List<OrderResponse>> getAllOrderByState(@PathVariable String state){
        return restaurantClient.getAllOrderByState(state,userRepository.findByEmail(userLoginApplication()).get().getId());
    }
    @Operation(summary = "take Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order taken", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/employee/takeOrder/{id}")
    public ResponseEntity<Void> takeOrder(@PathVariable Long id){
        return restaurantClient.takeOrder(id,userRepository.findByEmail(userLoginApplication()).get().getId() );
    }
    @Operation(summary = "Change Order to ready to deliver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order ready to deliver", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/employee/readyToDeliver/{id}")
    public ResponseEntity<Void> readyToDeliver(@PathVariable Long id){
        return restaurantClient.readyToDeliver(id,userRepository.findByEmail(userLoginApplication()).get().getId());
    }
    @Operation(summary = "Change Order deliver")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deliver", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/employee/deliverOrder/{id}")
    public ResponseEntity<Void> deliverOrder(@PathVariable Long id){
        return restaurantClient.deliverOrder(id,userRepository.findByEmail(userLoginApplication()).get().getId() );
    }
    @Operation(summary = "cancel Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order cancel", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/client/cancelOrder/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id){
        return restaurantClient.cancelOrder(id,userRepository.findByEmail(userLoginApplication()).get().getId() );
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
