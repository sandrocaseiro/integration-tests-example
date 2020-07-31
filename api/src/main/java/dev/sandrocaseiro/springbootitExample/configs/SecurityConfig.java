package dev.sandrocaseiro.springbootitExample.configs;

import dev.sandrocaseiro.springbootitExample.filters.CustomAuthorizationFilter;
import dev.sandrocaseiro.springbootitExample.filters.ServletErrorFilter;
import dev.sandrocaseiro.springbootitExample.repositories.CorsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ServletErrorFilter errorFilter;
    private final CorsProperties corsProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().sameOrigin();

        if (corsProperties.isEnabled())
            http.cors().and().authorizeRequests(r -> r.requestMatchers(CorsUtils::isPreFlightRequest).permitAll());

        http.authorizeRequests(r ->
                r
                    .antMatchers(
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml**",
                        "/swagger-ui/**",
                        "/swagger-ui.html**",
                        "/h2-console/**").permitAll()
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

    @Bean
    @ConditionalOnProperty(value="cors.enabled", havingValue = "true", matchIfMissing = false)
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods()));
        config.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders()));
        config.setExposedHeaders(Arrays.asList(corsProperties.getExposedHeaders()));
        config.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins()));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    private CustomAuthorizationFilter getAuthorizationFilter() throws Exception {
        return new CustomAuthorizationFilter(authenticationManager());
    }
}
