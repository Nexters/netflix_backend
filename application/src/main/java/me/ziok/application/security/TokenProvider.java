package me.ziok.application.security;

import org.springframework.security.core.Authentication;

public interface TokenProvider {

    String generateToken(Authentication authentication);
    Long getAccountIdFromToken(String token);
    boolean validateToken(String authToken);
}
