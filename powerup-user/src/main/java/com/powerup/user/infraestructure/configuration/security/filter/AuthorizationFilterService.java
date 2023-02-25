package com.powerup.user.infraestructure.configuration.security.filter;

import com.powerup.user.infraestructure.configuration.security.usecase.SecurityService;
import io.jsonwebtoken.JwtException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilterService extends BasicAuthenticationFilter {
    private final SecurityService securityService;

    public AuthorizationFilterService(AuthenticationManager authenticationManager, SecurityService securityService) {
        super(authenticationManager);
        this.securityService = securityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        try {
            if(!securityService.requiresAuthentication(header)){
                chain.doFilter(request,response);
                return;
            }

            if(securityService.validate(header)){
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    securityService.getUsername(header),
                    null,
                    securityService.getRoles(header)
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            chain.doFilter(request, response);
        }catch (AccessDeniedException | JwtException e) {
            securityService.errorResponseBuilder(response);
        }

    }
}
