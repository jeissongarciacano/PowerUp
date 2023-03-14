package com.powerup.square.infraestructure.configuration.userclient;

import com.powerup.square.application.dto.user.UserRequest;
import com.powerup.square.application.dto.user.UserResponse;
import com.powerup.square.application.dto.user.security.AuthenticationRequest;
import com.powerup.square.application.dto.user.security.AuthenticationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="user-service",url = "http://localhost:8090/api/v1/" )
public interface UserClient {
    @RequestMapping(method = RequestMethod.POST, value = "auth/authenticate")
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);
    @RequestMapping(method = RequestMethod.POST, value = "user/admin/create_owner")
    ResponseEntity<UserResponse> saveUserEntityOwner(@Validated @RequestBody UserRequest userRequest);
    @RequestMapping(method = RequestMethod.POST, value = "user/owner/create_employee")
    ResponseEntity<UserResponse> saveUserEntityEmployee(@Validated @RequestBody UserRequest userRequest);
    @RequestMapping(method = RequestMethod.POST, value = "user/client")
    ResponseEntity<UserResponse> saveUserEntityClient(@Validated @RequestBody UserRequest userRequest);
    @RequestMapping(method = RequestMethod.GET, value = "user/id/{id}")
    UserResponse getUserById(@PathVariable Long id);
    @RequestMapping(method = RequestMethod.GET, value = "user/email/{email}")
    UserResponse getUserByEmail(@PathVariable String email);
}
