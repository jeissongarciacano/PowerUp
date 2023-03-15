package com.powerup.square.infraestructure.input.rest;


import com.powerup.square.application.dto.plate.*;
import com.powerup.square.application.handler.IPlateHandler;
import com.powerup.square.infraestructure.configuration.userclient.UserClient;
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
@RequestMapping("/plates")
@RequiredArgsConstructor
public class PlateRestController {

    private final IPlateHandler plateHandler;
    private final UserClient userClient;

    @Operation(summary = "Add a new plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plate created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/owner/create_plate")
    public ResponseEntity<PlateResponse> savePlateEntity(@Validated @RequestBody PlateRequest plateRequest){
        plateRequest.setIdOwner(userClient.getUserByEmail(userLoginApplication()).getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(plateHandler.savePlate(plateRequest));
    }
    @Operation(summary = "change plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate modified", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/owner/put_plate")
    public ResponseEntity<PlateResponse> editPlate(@RequestBody PlateUpdatingRequest plateUpdatingRequest){
        plateUpdatingRequest.setIdOwner(userClient.getUserByEmail(userLoginApplication()).getId());
        return ResponseEntity.status(HttpStatus.OK).body(plateHandler.updatePlate(plateUpdatingRequest));
    }
    @Operation(summary = "activate plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plate disable or active", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate doesn't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PutMapping("/owner/activate_plate")
    public ResponseEntity<PlateResponse> activatePlate(@Validated @RequestBody ActivatePlateRequest activatePlateRequest){
        activatePlateRequest.setIdOwner(userClient.getUserByEmail(userLoginApplication()).getId());
        return ResponseEntity.status(HttpStatus.OK).body(plateHandler.activePlate(activatePlateRequest));
    }
    @Operation(summary = "Get plates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Plates found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Plates don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @GetMapping("/client/get_plates/{amount}/{page}/{sort}/{idRestaurant}")
    public ResponseEntity<List<PlateResponse>> getAllPlates(@PathVariable Long amount, @PathVariable Long page,@PathVariable String sort, @PathVariable Long idRestaurant){
        if(amount <= 0L || page < 0L || sort.isBlank() || sort.isEmpty() || idRestaurant <= 0L) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.FOUND).body(plateHandler.getPlates(amount, page, sort, idRestaurant));
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
