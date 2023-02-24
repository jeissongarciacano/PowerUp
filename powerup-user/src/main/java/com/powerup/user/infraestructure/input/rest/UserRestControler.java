package com.powerup.user.infraestructure.input.rest;

import com.powerup.user.application.dto.RestauranteRequest;
import com.powerup.user.application.dto.UserRequest;
import com.powerup.user.application.dto.UserResponse;
import com.powerup.user.application.handler.IUserHandler;
import com.powerup.user.infraestructure.RestaurateClientFeign.RestauranteClient.RestauranteClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserRestControler {
    private final IUserHandler userHandler;
    @Autowired
    private RestauranteClient restauranteClient;
    @Operation(summary = "Add a new Owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "USer already exists", content = @Content)
    })
    @PostMapping("/Propietario/{idRole}")
    public ResponseEntity<Void> saveUserEntityOwner(@Validated @RequestBody UserRequest userRequest, @PathVariable Long idRole){
        if(idRole == 0) {
            userHandler.saveUser(userRequest, 1L);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Operation(summary = "Add a new Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "USer already exists", content = @Content)
    })
    @PostMapping("/Employee/{idRole}")
    public ResponseEntity<Void> saveUserEntityEmployee(@Validated @RequestBody UserRequest userRequest, @PathVariable Long idRole){
        if(idRole == 0){
                userHandler.saveUser(userRequest, 2L);
                return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @Operation(summary = "Add a new Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "USer already exists", content = @Content)
    })
    @PostMapping("/Client")
    public ResponseEntity<Void> saveUserEntityClient(@Validated @RequestBody UserRequest userRequest){
        userHandler.saveUser(userRequest, 3L);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "USer already exists", content = @Content)
    })
    @GetMapping("/GetUser/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        return userHandler.getUser(id);
    }

    @Operation(summary = "get User by name and lastname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "USer already exists", content = @Content)
    })
    @GetMapping("/GetUser/{name}/{lastName}")
    public UserResponse getUserByNameLastName(@PathVariable String name, @PathVariable String lastName){
        return null;
    }
    @PostMapping("/restaurante")
    public  ResponseEntity<RestauranteRequest> saveRestaurante(@RequestBody RestauranteRequest restauranteRequest){
        RestauranteRequest restaurante = restauranteClient.saveRestaurante(restauranteRequest).getBody();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restauranteRequest);
    }

}
