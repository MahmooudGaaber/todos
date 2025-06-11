package com.luv2code.todos.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Objects;

public interface JwtService {
    String extractUsername(String token);

    boolean isTokenValid(String token , UserDetails userDetails);

    String generateToken(Map<String, Object> claims , UserDetails userDetails);
}
