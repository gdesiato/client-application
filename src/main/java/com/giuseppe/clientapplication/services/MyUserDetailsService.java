package com.giuseppe.clientapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.getUser(username);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                    true, true, true, true, authorities);
        } else {
            return null;
        }
    }
}
