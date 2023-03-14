package com.powerup.square.infraestructure.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/user/login", "/swagger-ui/**", "/swagger-resources/**", "/user/client", "/v3/api-docs/**", "/v2/api-docs/**")
                .permitAll()
                .antMatchers("/user/admin/**", "/restaurants/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/user/owner/**", "/plates/owner/**").hasAuthority("ROLE_OWNER")
                .antMatchers("/order/employee/**").hasAuthority("ROLE_EMPLOYEE")
                .antMatchers("/restaurants/client/**", "/plates/client/**", "/order/client/**").hasAuthority("ROLE_CLIENT")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


}