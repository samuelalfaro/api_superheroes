package org.sam.prueba.security.authentication;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class ApiKeyAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final String headerName;

    public ApiKeyAuthenticationFilter(final String headerName) {
        this.headerName = headerName;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(headerName);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        // No hacen falta credenciales con la API key
        return null;
    }
}
