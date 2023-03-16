package com.powerup.user.infraestructure.input.rest;

import com.powerup.user.application.dto.UserRequest;
import com.powerup.user.application.dto.UserResponse;
import com.powerup.user.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor

public class UserRestController {
    private final IUserHandler userHandler;
    @Operation(summary = "Add a new Owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/admin/create_owner")
    public ResponseEntity<UserResponse> saveUserEntityOwner(@Validated @RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userHandler.saveUser(userRequest, 1L));
    }
    @Operation(summary = "Add a new Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/owner/create_employee")
    public ResponseEntity<UserResponse> saveUserEntityEmployee(@Validated @RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userHandler.saveUser(userRequest, 2L));

    }
    @Operation(summary = "Add a new Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/client")
    public ResponseEntity<UserResponse> saveUserEntityClient(@Validated @RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userHandler.saveUser(userRequest, 3L));
    }

    @Operation(summary = "get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "User found", content = @Content),
            @ApiResponse(responseCode = "404", description = "User don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)

    })
    @GetMapping("/id/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        return userHandler.getUser(id);
    }

    @Operation(summary = "get User by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "User found", content = @Content),
            @ApiResponse(responseCode = "404", description = "User don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @GetMapping("/email/{email}")
    public UserResponse getUserByEmail(@PathVariable String email){
        return userHandler.getUserByEmail(email);
    }
}
