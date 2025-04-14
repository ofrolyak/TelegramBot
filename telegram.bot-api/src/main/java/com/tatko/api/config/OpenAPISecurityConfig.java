package com.tatko.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPISecurityConfig {

    /**
     * Url for OAuth2 provider (KeyCloak).
     */
    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    /**
     * Name custom realm (has to be created on OAuth2 provider site (KeyCloak)).
     */
    @Value("${keycloak.realm}")
    private String realm;

    /**
     * Name for OAuth2 schema.
     */
    private static final String OAUTH_SCHEME_NAME = "my_oAuth_security_schema";

    /**
     * OpenAPI itself.
     * @return OpenAPI itself.
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().components(new Components()
                        .addSecuritySchemes(
                                OAUTH_SCHEME_NAME, createOAuthScheme()))
                .addSecurityItem(
                        new SecurityRequirement().addList(OAUTH_SCHEME_NAME))
                .info(new Info().title("Todos Management Service")
                        .description("A service providing todos.")
                        .version("1.0"));
    }

    private SecurityScheme createOAuthScheme() {
        OAuthFlows flows = createOAuthFlows();
        return new SecurityScheme().type(SecurityScheme.Type.OAUTH2)
                .flows(flows);
    }

    private OAuthFlows createOAuthFlows() {
        OAuthFlow flow = createAuthorizationCodeFlow();
        return new OAuthFlows().implicit(flow);
//        return new OAuthFlows().password(flow);
    }

    private OAuthFlow createAuthorizationCodeFlow() {
        return new OAuthFlow()
                .authorizationUrl(authServerUrl + "/realms/"
                        + realm + "/protocol/openid-connect/auth");
//                .scopes(new Scopes()
//                .addString("telegram_read_access", "read data")
//                        .addString("telegram_write_access", "modify data"));
    }

}
