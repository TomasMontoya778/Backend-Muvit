package com.muvit.MUVIT.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.muvit.MUVIT.infrastructure.helpers.JwtFilter;
import com.muvit.MUVIT.util.enums.RolEnum;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
@Autowired
        private final AuthenticationProvider authenticationProvider;
        @Autowired
        private final JwtFilter jwtFilter;
    private final String[] PUBLIC_RESOURCES = {"/services/public/get", "/auth/**"};

    private final String[] ADMIN_RESOURCES = { "/register/driver"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
        .csrf(csrf -> csrf.disable()) 
        .authorizeHttpRequests(authRequest -> authRequest
        .requestMatchers(PUBLIC_RESOURCES).permitAll()
        .anyRequest().authenticated())
        .sessionManagement(seesionManager -> seesionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build(); 
    }



}