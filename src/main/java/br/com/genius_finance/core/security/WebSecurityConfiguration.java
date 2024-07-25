package br.com.genius_finance.core.security;

import br.com.genius_finance.core.config.KeycloakConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    private final KeycloakConfiguration keycloakConfiguration;

    @Autowired
    public WebSecurityConfiguration(KeycloakConfiguration keycloakConfiguration) {
        this.keycloakConfiguration = keycloakConfiguration;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationEntryPoint entryPoint,
                                                   CustomAccessDenied accessDenied) throws Exception {

        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/*/api-docs/**").permitAll()
                        .requestMatchers("/docs").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
        );

        http.csrf(AbstractHttpConfigurer::disable);

        http.oauth2ResourceServer(
                httpSecurityOAuth2ResourceServerConfigurer ->
                {
                    httpSecurityOAuth2ResourceServerConfigurer.authenticationEntryPoint(entryPoint);
                    httpSecurityOAuth2ResourceServerConfigurer.accessDeniedHandler(accessDenied);
                    httpSecurityOAuth2ResourceServerConfigurer.jwt(
                            jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(new KeycloakJwtRolesConverter(keycloakConfiguration))
                    );
                }
        );

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation(keycloakConfiguration.getIssuerUrl());
    }

}