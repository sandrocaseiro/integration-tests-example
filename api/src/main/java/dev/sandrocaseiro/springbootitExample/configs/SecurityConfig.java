package dev.sandrocaseiro.springbootitExample.configs;

import dev.sandrocaseiro.springbootitExample.filters.CustomAuthorizationFilter;
import dev.sandrocaseiro.springbootitExample.filters.ServletErrorFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ServletErrorFilter errorFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().sameOrigin();

        http.authorizeRequests(r ->
                r
                    .antMatchers(
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml**",
                        "/swagger-ui/**",
                        "/swagger-ui.html**").permitAll()
                    .antMatchers("/v*/token/**").anonymous()
                    .antMatchers(HttpMethod.POST, "/v?/users").permitAll()
                    .antMatchers("/v1/**").authenticated()
                    .anyRequest().denyAll()
            )
            .exceptionHandling(c ->
                c
                    .authenticationEntryPoint(errorFilter)
                    .accessDeniedHandler(errorFilter)
            )
            .addFilterBefore(errorFilter, CorsFilter.class)
            .addFilterAfter(getAuthorizationFilter(), CorsFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }

    private CustomAuthorizationFilter getAuthorizationFilter() throws Exception {
        return new CustomAuthorizationFilter(authenticationManager());
    }
}
