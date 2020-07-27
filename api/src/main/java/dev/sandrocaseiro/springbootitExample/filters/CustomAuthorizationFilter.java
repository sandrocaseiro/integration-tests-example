package dev.sandrocaseiro.springbootitExample.filters;

import dev.sandrocaseiro.springbootitExample.security.UsuarioPrincipal;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class CustomAuthorizationFilter extends BasicAuthenticationFilter {
    private static final String AUTH_VALUE = "teste";

    public CustomAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(header) || !AUTH_VALUE.equalsIgnoreCase(header)) {
            chain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(getAuthentication(header));
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        UsuarioPrincipal principal = new UsuarioPrincipal(header);

        return new UsernamePasswordAuthenticationToken(principal, null, Collections.emptyList());
    }
}
