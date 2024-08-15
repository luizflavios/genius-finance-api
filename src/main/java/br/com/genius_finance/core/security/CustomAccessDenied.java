package br.com.genius_finance.core.security;

import br.com.genius_finance.model.dto.base.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDenied implements AccessDeniedHandler {

    protected static final String ACCESS_DENIED = "Access Denied";

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        var errorResponse = ErrorDTO.builder()
                .error(HttpStatus.FORBIDDEN.name())
                .timestamp(System.currentTimeMillis())
                .detail(ACCESS_DENIED)
                .build();

        var mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), errorResponse);
    }

}