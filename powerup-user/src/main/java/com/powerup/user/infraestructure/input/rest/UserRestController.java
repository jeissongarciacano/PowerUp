package com.powerup.user.infraestructure.input.rest;

import com.powerup.user.application.dto.UserRequest;
import com.powerup.user.application.dto.UserResponse;
import com.powerup.user.application.handler.IUserHandler;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor

public class UserRestController {
    private final IUserHandler userHandler;
    private final RestaurantClient restaurantClient;
    @Operation(summary = "Add a new Owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PostMapping("/admin/createOwner")
    public ResponseEntity<Void> saveUserEntityOwner(@Validated @RequestBody UserRequest userRequest){
        userHandler.saveUser(userRequest, 1L);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Add a new Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PostMapping("/owner/createEmployee")
    public ResponseEntity<Void> saveUserEntityEmployee(@Validated @RequestBody UserRequest userRequest){
        userHandler.saveUser(userRequest, 2L);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @Operation(summary = "Add a new Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PostMapping("/client")
    public ResponseEntity<Void> saveUserEntityClient(@Validated @RequestBody UserRequest userRequest){
        userHandler.saveUser(userRequest, 3L);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @GetMapping("/GET/UserById/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        System.out.println(id);
        UserResponse userResponse = userHandler.getUser(id);
        return userResponse;
    }

    @Operation(summary = "get User by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @GetMapping("/GET/UserByEmail/{email}")
    public UserResponse getUserByEmail(@PathVariable String email){
        System.out.println(email);
        UserResponse userResponse = userHandler.getUserByEmail(email);
        return userResponse;
    }
    @Operation(summary = "Get User by rol name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @GetMapping("/GET/UsersByRoleName/{name}")
    public List<UserResponse> getAllUsers(@PathVariable String name){
        return userHandler.findClientByRol(name);
    }


}
