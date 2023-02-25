package com.powerup.user.infraestructure.configuration.security.usecase.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powerup.user.domain.exception.ErrorCodesEnum;
import com.powerup.user.infraestructure.configuration.security.SecurityConfig;
import com.powerup.user.infraestructure.configuration.security.usecase.SecurityService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    public String create(Authentication authentication) {
        String username = ((User) authentication.getPrincipal()).getUsername();

        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();

        Claims claims = Jwts.claims();
        claims.put("roles", roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .signWith(SecurityConfig.SECRET_KEY)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 3600000))
            .compact();
    }

    @Override
    public boolean validate(String token) {
         try{
             getClaims(token);
             return true;
         }catch (JwtException | IllegalArgumentException e){
             return false;
         }
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(SecurityConfig.SECRET_KEY)
            .build()
            .parseClaimsJws(resolve(token))
            .getBody();
    }

    @Override
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public Collection<? extends GrantedAuthority> getRoles(String token) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List) getClaims(token).get("roles");

        return authorities.stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String resolve(String token) {
        if(requiresAuthentication(token)){
            return token.replace("Bearer ", "");
        }
        return null;
    }

    @Override
    public boolean requiresAuthentication(String header) {
        return header != null && header.startsWith("Bearer ");
    }

    @Override
    public void errorResponseBuilder(HttpServletResponse response) throws IOException {
        ErrorCodesEnum code = ErrorCodesEnum.INVALID_CREDENTIALS;
        Map<String, Object> body = new HashMap<>();

        body.put("message", code.getMessage());
        body.put("code", code.getCode());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(code.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
