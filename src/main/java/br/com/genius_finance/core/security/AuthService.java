package br.com.genius_finance.core.security;

import br.com.genius_finance.core.config.KeycloakConfiguration;
import br.com.genius_finance.model.dto.base.LoginRequestDTO;
import br.com.genius_finance.model.dto.base.TokenDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class AuthService {

    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    private static final String ACCESS_TOKEN = "Access-Token";
    private static final String EXPIRES_IN = "Expires-In";

    private final KeycloakConfiguration keycloakConfiguration;
    private final RestTemplate restTemplate;

    @Autowired
    public AuthService(KeycloakConfiguration keycloakConfiguration, RestTemplate restTemplate) {
        this.keycloakConfiguration = keycloakConfiguration;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<TokenDTO> login(LoginRequestDTO request,
                                          HttpServletResponse servletResponse) {

        log.info("Start to get access token");

        var accessTokenResponse = this.getAccessToken(request);

        servletResponse.addHeader(ACCESS_TOKEN, accessTokenResponse.getToken());
        servletResponse.addHeader(EXPIRES_IN, String.valueOf(accessTokenResponse.getExpiresIn()));

        var tokenDTO = TokenDTO.builder()
                .accessToken(accessTokenResponse.getToken())
                .refreshToken(accessTokenResponse.getRefreshToken())
                .expiresIn(accessTokenResponse.getExpiresIn())
                .build();

        return ResponseEntity.ok().body(tokenDTO);
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

    public ResponseEntity<TokenDTO> refreshToken(HttpServletResponse servletResponse) {
        log.info("Start to refresh access token");

        //refreshTokenFromCache
        var refreshTokenFromCache = "";
        var tokenDto = this.getRefreshToken(refreshTokenFromCache);

        servletResponse.addHeader(ACCESS_TOKEN, tokenDto.getAccessToken());
        servletResponse.addHeader(EXPIRES_IN, String.valueOf(tokenDto.getExpiresIn()));

        return ResponseEntity.ok().body(tokenDto);
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
}