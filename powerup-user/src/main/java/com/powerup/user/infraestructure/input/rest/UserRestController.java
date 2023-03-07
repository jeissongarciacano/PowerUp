package com.powerup.user.infraestructure.input.rest;

import com.powerup.user.application.dto.EmployeeRequest;
import com.powerup.user.application.dto.UserRequest;
import com.powerup.user.application.dto.UserResponse;
import com.powerup.user.application.handler.IUserHandler;
import com.powerup.user.application.handler.impl.UserHandler;
import com.powerup.user.infraestructure.configuration.RestauranteClient.RestaurantClient;
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
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor

public class UserRestController {
    private final IUserHandler userHandler;
    private final RestaurantClient restaurantClient;
    @Operation(summary = "Add a new Owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/admin/createOwner")
    public ResponseEntity<Void> saveUserEntityOwner(@Validated @RequestBody UserRequest userRequest){
        userHandler.saveUser(userRequest, 1L);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Operation(summary = "Add a new Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/owner/createEmployee")
    public ResponseEntity<Void> saveUserEntityEmployee(@Validated @RequestBody UserRequest userRequest){
        userHandler.saveUser(userRequest, 2L);
        UserResponse userResponse = userHandler.getUserByEmail(userRequest.getEmail());
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setIdUser(userResponse.getId());
        employeeRequest.setIdOwner(userHandler.getUserByEmail(userLoginApplication()).getId());
        employeeRequest.setField("Employee");
        restaurantClient.createEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @Operation(summary = "Add a new Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/client")
    public ResponseEntity<Void> saveUserEntityClient(@Validated @RequestBody UserRequest userRequest){
        userHandler.saveUser(userRequest, 3L);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
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
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @GetMapping("/GET/UserByEmail/{email}")
    public UserResponse getUserByEmail(@PathVariable String email){
        System.out.println(email);
        UserResponse userResponse = userHandler.getUserByEmail(email);
        return userResponse;
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
