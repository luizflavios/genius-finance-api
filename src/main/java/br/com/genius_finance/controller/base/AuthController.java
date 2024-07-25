package br.com.genius_finance.controller.base;

import br.com.genius_finance.core.security.AuthService;
import br.com.genius_finance.model.dto.base.LoginRequestDTO;
import br.com.genius_finance.model.dto.base.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    @Operation(description = "Login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestDTO request,
                                          HttpServletResponse servletResponse) {
        return authService.login(request, servletResponse);
    }

    @PostMapping(value = "/refresh-token")
    @Operation(description = "Refresh Token")
    public ResponseEntity<TokenDTO> refreshToken(HttpServletResponse servletResponse) {
        return authService.refreshToken(servletResponse);
    }
}