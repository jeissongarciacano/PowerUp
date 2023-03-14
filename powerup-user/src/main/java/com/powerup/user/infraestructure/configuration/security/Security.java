package com.powerup.user.infraestructure.configuration.security;

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
                .antMatchers("/api/v1/auth/**", "/swagger-ui/**", "/swagger-resources/**",
                        "/api/v1/user/client/**", "/v3/api-docs/**", "/v2/api-docs/**", "/api/v1/user/email/**"
                        , "/api/v1/user/id/**")
                .permitAll()
                .antMatchers("/api/v1/user/admin/**", "/api/v1/square/admin/**").permitAll()
                .antMatchers("/api/v1/user/owner/**", "/api/v1/square/owner/**").permitAll()
                .antMatchers("/api/v1/square/employee**").permitAll()
                .antMatchers("/api/v1/square/client/**").permitAll()
//                .antMatchers("/api/v1/user/admin/**", "/api/v1/square/admin/**").hasAuthority("ROLE_ADMIN")
//                .antMatchers("/api/v1/user/owner/**", "/api/v1/square/owner/**").hasAuthority("ROLE_OWNER")
//                .antMatchers("/api/v1/square/employee**").hasAuthority("ROLE_EMPLOYEE")
//                .antMatchers("/api/v1/square/client/**").hasAuthority("ROLE_CLIENT")
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