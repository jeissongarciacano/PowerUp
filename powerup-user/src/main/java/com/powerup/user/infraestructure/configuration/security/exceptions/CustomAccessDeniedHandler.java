package com.powerup.user.infraestructure.configuration.security.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powerup.user.domain.exception.ErrorCodesEnum;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorCodesEnum errorCode = ErrorCodesEnum.INVALID_CREDENTIALS;

        Map<String, Object> body = new HashMap<>();

        body.put("message", errorCode.getMessage());
        body.put("code", errorCode.getCode());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(errorCode.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
