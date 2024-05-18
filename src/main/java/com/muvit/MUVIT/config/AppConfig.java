package com.muvit.MUVIT.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.muvit.MUVIT.domain.repositories.RolRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class AppConfig {


    @Autowired
    private final RolRepository rolRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(){
return username -> this.rolRepository.findByNameUser(username).orElseThrow((()-> new UsernameNotFoundException("EL mensaje no fue encontrado")));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(this.passwordEncoder());  
        authenticationProvider.setUserDetailsService(this.userDetailsService());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
