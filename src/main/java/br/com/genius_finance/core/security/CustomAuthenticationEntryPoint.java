package br.com.genius_finance.core.security;

import br.com.genius_finance.model.dto.base.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static br.com.genius_finance.core.security.CustomAccessDenied.ACCESS_DENIED;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        var errorResponse = ErrorDTO.builder()
                .error(HttpStatus.UNAUTHORIZED.name())
                .timestamp(System.currentTimeMillis())
                .detail(ACCESS_DENIED)
                .build();

        var mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), errorResponse);
    }
}