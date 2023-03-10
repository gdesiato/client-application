package com.giuseppe.clientapplication.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests(auth -> auth
//                        .antMatchers("/", "/register").permitAll()
//                        .anyRequest().authenticated())
//               .formLogin(login -> login.loginPage("/login").permitAll());

                .authorizeRequests(auth -> auth
                    .anyRequest().permitAll()) // allow all requests to be processed without authentication
                .formLogin(login -> login.loginPage("/login").permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


