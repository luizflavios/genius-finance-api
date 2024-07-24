package br.com.genius_finance.controller.base;

import br.com.genius_finance.core.security.AuthService;
import br.com.genius_finance.model.dto.base.LoginRequestDTO;
import br.com.genius_finance.model.dto.base.TokenDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestDTO request,
                                          HttpServletRequest servletRequest,
                                          HttpServletResponse servletResponse) {
        return authService.login(request, servletRequest, servletResponse);
    }

//    @PostMapping(value = "/refresh-token")
//    public ResponseEntity<Object> refreshToken(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
//        return authService.refreshToken(servletRequest, servletResponse);
//    }
}