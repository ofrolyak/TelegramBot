package com.tatko.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * AuthenticationEntryPoint instance.
     */
    @Autowired
    @Qualifier("delegatedAuthenticationEntryPoint")
    private AuthenticationEntryPoint authEntryPoint;

    /**
     * SecurityFilterChain itself.
     * @param http
     * @return SecurityFilterChain itself
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http)
            throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize -> {
            try {
                authorize
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**")
                        .permitAll()
                        .anyRequest().authenticated();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        http.oauth2ResourceServer(t -> {
            t.jwt(
                    jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(
                            keyCloakAuthConverter()
                    ));
        });

        http.sessionManagement(
                t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.exceptionHandling((exceptions) -> exceptions
                .authenticationEntryPoint(authEntryPoint)
        );

        return http.build();
    }


    private Converter<Jwt, ? extends
            AbstractAuthenticationToken> keyCloakAuthConverter() {

        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new AuthoritiesConverter());
        return converter;

    }

}
