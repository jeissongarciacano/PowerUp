package com.PowerUp.User.infraestructure.input.rest;

import com.PowerUp.User.application.dto.UserRequest;
import com.PowerUp.User.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor

public class UserRestControler {
    private final IUserHandler userHandler;

    @Operation(summary = "Add a new Owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "USer already exists", content = @Content)
    })
    @PostMapping("/Propietario/")
    public ResponseEntity<Void> saveUserEntityOwner(@RequestBody UserRequest userRequest){
        userHandler.saveUser(userRequest, 1L);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
