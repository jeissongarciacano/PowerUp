package com.powerup.user.infraestructure.configuration.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powerup.user.domain.config.Routes;
import com.powerup.user.domain.exception.ErrorCodesEnum;
import com.powerup.user.domain.model.User;
import com.powerup.user.infraestructure.configuration.security.usecase.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationFilterService extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final SecurityService securityService;

    public AuthenticationFilterService(AuthenticationManager authenticationManager, SecurityService securityService) {
        this.authenticationManager = authenticationManager;
        this.securityService = securityService;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(Routes.USER_PATH+Routes.LOGIN_PATH, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws SecurityException {
        String email = "";
        String password = "";
        try {
            User user =
                new ObjectMapper().readValue(request.getInputStream(), User.class);

            email = user.getEmail();
            password = user.getPassword();

        } catch (IOException e) {
            e.printStackTrace();
        }

        email = email.trim();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws SecurityException, IOException {
        String token = securityService.create(authResult);

        response.addHeader("Authorization", "Bearer "+token);

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);

        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        Map<String, Object> body = new HashMap<>();

        body.put("message", ErrorCodesEnum.AUTHENTICATION_ERROR.getMessage());
        body.put("code",ErrorCodesEnum.AUTHENTICATION_ERROR.getCode());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(ErrorCodesEnum.AUTHENTICATION_ERROR.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    }
}
