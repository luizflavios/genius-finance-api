package br.com.genius_finance.core.security;

import br.com.genius_finance.model.dto.base.LoginRequestDTO;
import br.com.genius_finance.model.dto.base.TokenDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String PASSWORD = "password";
    private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    private static final String ACCESS_TOKEN = "Access-Token";
    private static final String REFRESH_TOKEN = "Refresh-Token";
    private static final String EXPIRES_IN = "Expires-In";
    private static final String DEVICE_ID = "Device-Id";

    private final RestTemplate restTemplate;

    @Value("${keycloak.client-id}")
    private String kcClientId;
    @Value("${keycloak.client-secret}")
    private String kcClientSecret;
    @Value("${keycloak.get-token-url}")
    private String kcGetTokenUrl;

    public ResponseEntity<TokenDTO> login(LoginRequestDTO request,
                                          HttpServletRequest servletRequest,
                                          HttpServletResponse servletResponse) {

        log.info("Start to get access token");

        String deviceId = servletRequest.getHeader(DEVICE_ID);

        var tokenDTO = this.getAccessToken(request);

        servletResponse.addHeader(ACCESS_TOKEN, tokenDTO.getAccessToken());
        servletResponse.addHeader(EXPIRES_IN, String.valueOf(tokenDTO.getExpiresIn()));

//        sessionStorage.putCache(REFRESH_TOKEN, deviceId, tokenDTO.getRefreshToken(), 1800);

        return ResponseEntity.ok().body(tokenDTO);
    }

//    public ResponseEntity<Object> refreshToken(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
//        log.info("Start to refresh access token");
//
//        String deviceId = servletRequest.getHeader(DEVICE_ID);
//        String refreshToken = (String) sessionStorage.getCache(REFRESH_TOKEN, deviceId);
//
//        TokenDto tokenDto = this.getRefreshToken(refreshToken);
//
//        servletResponse.addHeader(ACCESS_TOKEN, tokenDto.getAccessToken());
//        servletResponse.addHeader(EXPIRES_IN, String.valueOf(tokenDto.getExpiresIn()));
//
//        sessionStorage.putCache(REFRESH_TOKEN, deviceId, tokenDto.getRefreshToken(), tokenDto.getRefreshExpiresIn());
//
//        return ResponseEntity.ok().body(BaseResponseDto.builder()
//                .status("SUCCESS")
//                .build());
//    }

    private TokenDTO getAccessToken(LoginRequestDTO request) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", PASSWORD);
        requestBody.add("client_id", kcClientId);
        requestBody.add("client_secret", kcClientSecret);
        requestBody.add("username", request.getUsername());
        requestBody.add(PASSWORD, request.getPassword());

        var response = restTemplate.postForEntity(kcGetTokenUrl,
                new HttpEntity<>(requestBody, headers), TokenDTO.class);

        return response.getBody();
    }

    private TokenDTO getRefreshToken(String refreshToken) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", GRANT_TYPE_REFRESH_TOKEN);
        requestBody.add("refresh_token", refreshToken);
        requestBody.add("client_id", kcClientId);
        requestBody.add("client_secret", kcClientSecret);

        var response = restTemplate.postForEntity(kcGetTokenUrl,
                new HttpEntity<>(requestBody, headers), TokenDTO.class);

        return response.getBody();
    }
}