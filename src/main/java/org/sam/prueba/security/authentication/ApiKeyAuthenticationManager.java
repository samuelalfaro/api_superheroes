package org.sam.prueba.security.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ApiKeyAuthenticationManager implements AuthenticationManager {

    private final String validApiKey;

    public ApiKeyAuthenticationManager(String validApiKey) {
        this.validApiKey = validApiKey;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();

        if (validApiKey.equals(principal)) {
            authentication.setAuthenticated(true);
            return authentication;
        }
        throw new BadCredentialsException("API Key no coincide.");
    }
}
