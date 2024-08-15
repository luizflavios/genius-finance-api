package br.com.genius_finance.client;

import br.com.genius_finance.core.config.KeycloakConfiguration;
import br.com.genius_finance.core.exception.CreateKeycloakUserException;
import br.com.genius_finance.model.dto.base.LoginRequestDTO;
import br.com.genius_finance.model.dto.base.TokenDTO;
import br.com.genius_finance.model.entity.PersonEntity;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static br.com.genius_finance.core.utils.KeycloakUtils.buildUserRepresentationFromPerson;
import static br.com.genius_finance.core.utils.KeycloakUtils.extractUserIdFromLocation;

@Slf4j
@Service
public class KeycloakClient {

    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

    private final Keycloak keycloak;
    private final KeycloakConfiguration keycloakConfiguration;
    private final RestTemplate restTemplate;

    @Autowired
    public KeycloakClient(Keycloak keycloak, KeycloakConfiguration keycloakConfiguration, RestTemplate restTemplate) {
        this.keycloak = keycloak;
        this.keycloakConfiguration = keycloakConfiguration;
        this.restTemplate = restTemplate;
    }

    public TokenDTO login(LoginRequestDTO request) {
        log.info("Start to get access token");

        var accessTokenResponse = this.getAccessToken(request);

        return TokenDTO.builder()
                .accessToken(accessTokenResponse.getToken())
                .refreshToken(accessTokenResponse.getRefreshToken())
                .expiresIn(accessTokenResponse.getExpiresIn())
                .build();
    }

    private TokenManager buildTokenManager(LoginRequestDTO loginRequestDTO) {
        var keycloakInstance = KeycloakBuilder.builder()
                .serverUrl(keycloakConfiguration.getServerUrl())
                .realm(keycloakConfiguration.getRealm())
                .clientId(keycloakConfiguration.getClientId())
                .clientSecret(keycloakConfiguration.getClientSecret())
                .username(loginRequestDTO.getUsername())
                .password(loginRequestDTO.getPassword())
                .grantType(OAuth2Constants.PASSWORD)
                .build();

        return keycloakInstance.tokenManager();
    }

    private AccessTokenResponse getAccessToken(LoginRequestDTO request) {
        return buildTokenManager(request).getAccessToken();
    }

    public TokenDTO refreshToken() {
        log.info("Start to refresh access token");
        //TODO: refreshTokenFromCache
        var refreshTokenFromCache = "";
        return this.getRefreshToken(refreshTokenFromCache);
    }

    private TokenDTO getRefreshToken(String refreshToken) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var requestBody = new LinkedMultiValueMap<>();
        requestBody.add(GRANT_TYPE, GRANT_TYPE_REFRESH_TOKEN);
        requestBody.add(GRANT_TYPE_REFRESH_TOKEN, refreshToken);
        requestBody.add(CLIENT_ID, keycloakConfiguration.getClientId());
        requestBody.add(CLIENT_SECRET, keycloakConfiguration.getClientSecret());

        var response = restTemplate.postForEntity(keycloakConfiguration.getTokenUrl(),
                new HttpEntity<>(requestBody, headers), TokenDTO.class);

        return response.getBody();
    }

    public UUID createKeycloakUser(PersonEntity personEntity) {
        var user = buildUserRepresentationFromPerson(personEntity);

        var response = keycloak
                .realm(keycloakConfiguration.getRealm())
                .users()
                .create(user);

        if (!HttpStatus.valueOf(response.getStatus()).is2xxSuccessful()) {
            throw new CreateKeycloakUserException();
        }

        return extractUserIdFromLocation(response);
    }
}