package com.tatko.api.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component("delegatedAuthenticationEntryPoint")
public class DelegatedAuthenticationEntryPoint
        implements AuthenticationEntryPoint {

    /**
     * HandlerExceptionResolver instance.
     */
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    /**
     * Make commence action.
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException authException)
            throws IOException, ServletException {
        resolver.resolveException(request, response, null, authException);
    }

}
