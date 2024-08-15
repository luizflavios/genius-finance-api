package br.com.genius_finance.core.config;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "keycloak")
@Getter
@Setter
@Configuration
public class KeycloakConfiguration {

    private String clientId;
    private String clientSecret;
    private String issuerUrl;
    private String tokenUrl;
    private String username;
    private String password;
    private String realm;
    private String serverUrl;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .password(password)
                .username(username)
                .serverUrl(serverUrl)
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }

}
