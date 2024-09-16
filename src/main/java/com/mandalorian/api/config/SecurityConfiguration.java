package com.mandalorian.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                    .requestMatchers(
                        "/v1/auth/refresh-token",
                        "/v1/auth/**",
                        "/api/swagger-ui.html",
                        "/api/swagger-ui/**",
                        "/api/v3/api-docs/**",
                        "/api/v3/api-docs",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html"
                    ).permitAll()
            )
            .authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                    .requestMatchers("/v1/admin/**").hasRole("ADMIN")
                    .requestMatchers(GET, "/v1/admin/**").hasAuthority("READ_PRIVILEGE")
                    .requestMatchers(POST, "/v1/admin/**").hasAuthority("CREATE_PRIVILEGE")
                    .requestMatchers(PUT, "/v1/admin/**").hasAuthority("UPDATE_PRIVILEGE")
                    .requestMatchers(PATCH, "/v1/admin/**").hasAuthority("UPDATE_PRIVILEGE")
                    .requestMatchers(DELETE, "/v1/admin/**").hasAuthority("DELETE_PRIVILEGE")
                    .requestMatchers("/v1/user/**").hasRole("USER")
                    .requestMatchers("/v1/admin/**").hasRole("MANAGER")
                    .requestMatchers(GET, "/v1/admin/**").hasAuthority("READ_PRIVILEGE")
                    .requestMatchers(POST, "/v1/admin/**").hasAuthority("CREATE_PRIVILEGE")
                    .anyRequest()
                    .authenticated()
            )
            .sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .logout((logout) ->
                logout.logoutUrl("/v1/auth/sign-out")
                    .invalidateHttpSession(true)
                    .addLogoutHandler(logoutHandler)
                    .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
            );

        return http.build();
    }
}
