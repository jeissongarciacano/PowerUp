package com.powerup.square.infraestructure.input.rest;

import com.powerup.square.application.dto.user.EmployeeRequest;
import com.powerup.square.application.dto.user.UserRequest;
import com.powerup.square.application.dto.user.UserResponse;
import com.powerup.square.application.dto.user.security.AuthenticationRequest;
import com.powerup.square.application.dto.user.security.AuthenticationResponse;
import com.powerup.square.application.handler.impl.EmployeeHandler;
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

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserClient userClient;
    private final EmployeeHandler employeeHandler;
    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userClient.authenticate(request).getBody());
    }
    @Operation(summary = "Add a new Owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/admin/create_owner")
    public ResponseEntity<UserResponse> saveUserEntityOwner(@Validated @RequestBody UserRequest userRequest){
    return ResponseEntity.status(HttpStatus.CREATED).body(userClient.saveUserEntityOwner(userRequest).getBody());
    }
    @Operation(summary = "Add a new Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @PostMapping("/owner/create_employee/{field}")
    public ResponseEntity<UserResponse> saveUserEntityEmployee(@Validated @RequestBody UserRequest userRequest, @PathVariable String field){
        UserResponse userResponse = userClient.saveUserEntityEmployee(userRequest).getBody();
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setIdUser(userResponse.getId());
        employeeRequest.setIdOwner(getUserByEmail(userLoginApplication()).getBody().getId());
        if(field.isBlank() || field.isEmpty()) employeeRequest.setField("Employee");
        else employeeRequest.setField(field);
        employeeHandler.saveEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);

    }
    @Operation(summary = "Add a new Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
    })
    @PostMapping("/client")
    public ResponseEntity<UserResponse> saveUserEntityClient(@Validated @RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userClient.saveUserEntityClient(userRequest).getBody());
    }

    @Operation(summary = "get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "User found", content = @Content),
            @ApiResponse(responseCode = "404", description = "User don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)

    })
    @GetMapping("/get_user/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userClient.getUserById(id));
    }

    @Operation(summary = "get User by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "User found", content = @Content),
            @ApiResponse(responseCode = "404", description = "User don't exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "403", description = "No authorized", content = @Content)
    })
    @GetMapping("/get_user/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(userClient.getUserByEmail(email));
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
