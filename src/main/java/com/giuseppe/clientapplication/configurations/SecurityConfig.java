package com.giuseppe.clientapplication.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(auth -> auth
                        .antMatchers("/", "/register").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/login").permitAll());

        return http.build();
    }

}
