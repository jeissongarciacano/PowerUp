package com.powerup.user.infraestructure.configuration.security.usecase;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public interface SecurityService {
    public String create(Authentication authentication);
    public boolean validate(String token);
    public Claims getClaims(String token);
    public String getUsername(String token);
    public Collection<? extends GrantedAuthority> getRoles(String token);
    public String resolve(String token);
    public boolean requiresAuthentication(String header);
    public void errorResponseBuilder(HttpServletResponse response) throws IOException;
}
