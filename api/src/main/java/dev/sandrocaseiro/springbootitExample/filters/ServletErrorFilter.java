package dev.sandrocaseiro.springbootitExample.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class ServletErrorFilter extends OncePerRequestFilter implements AccessDeniedHandler, AuthenticationEntryPoint {
    private final HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        resolver.resolveException(request, response, null, e);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        resolver.resolveException(request, response, null, e);
    }
}
