package me.ziok.application.security;

import org.springframework.security.core.Authentication;

public class TokenProviderImpl implements TokenProvider {
    @Override
    public String createToken(Authentication authentication) {
        return null;
    }

    @Override
    public Long getAccountIdFromToken(String token) {
        return null;
    }

    @Override
    public boolean validateToken(String authToken) {
        return false;
    }
}
