package com.powerup.user.infraestructure.configuration.security;

import com.powerup.user.domain.config.Routes;
import com.powerup.user.infraestructure.configuration.security.exceptions.CustomAccessDeniedHandler;
import com.powerup.user.infraestructure.configuration.security.filter.AuthenticationFilterService;
import com.powerup.user.infraestructure.configuration.security.filter.AuthorizationFilterService;
import com.powerup.user.infraestructure.configuration.security.handler.SecurityDetailsService;
import com.powerup.user.infraestructure.configuration.security.usecase.SecurityService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Key;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityDetailsService securityDetailsService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityDetailsService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, Routes.USER_PATH).hasAuthority("Admin")

            .antMatchers(HttpMethod.GET, Routes.USER_PATH+Routes.FIND_USER_BY_IDENTIFICATION_PATH).hasAuthority("Admin")
            .antMatchers(HttpMethod.PUT, Routes.USER_PATH+Routes.EDIT_USER_PATH).authenticated()

            .antMatchers(HttpMethod.PATCH, Routes.USER_PATH+Routes.ACTIVATE_USER_PATH).hasAuthority("Admin")
            .antMatchers(HttpMethod.PATCH, Routes.USER_PATH+Routes.DEACTIVATE_USER_PATH).authenticated()
            .anyRequest().permitAll()
            .and()
            .addFilter(new AuthenticationFilterService(authenticationManager(), securityService))
            .addFilter(new AuthorizationFilterService(authenticationManager(), securityService))
            .exceptionHandling().disable();
    }

}
