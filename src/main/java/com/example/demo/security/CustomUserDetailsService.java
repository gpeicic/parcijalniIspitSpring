package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Ovdje implementiraš dohvat korisnika, npr. iz baze

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Ovo je primjer, ti stavi svoj dohvat korisnika i rola
        if(username.equals("gabi")){
            return org.springframework.security.core.userdetails.User
                    .withUsername("gabi")
                    .password("{noop}password") // {noop} znači da nije enkodirano - koristi BCrypt u produkciji!
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
