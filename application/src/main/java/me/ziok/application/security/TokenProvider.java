package me.ziok.application.security;

import org.springframework.security.core.Authentication;

public interface TokenProvider {

    String createToken(Authentication authentication);
    Long getAccountIdFromToken(String token);
    boolean validateToken(String authToken);
}
