package br.com.genius_finance.controller.base;

import br.com.genius_finance.model.dto.base.LoginRequestDTO;
import br.com.genius_finance.model.dto.base.TokenDTO;
import br.com.genius_finance.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    @Operation(description = "Login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok().body(userService.login(request));
    }

    @PostMapping(value = "/refresh-token")
    @Operation(description = "Refresh Token")
    public ResponseEntity<TokenDTO> refreshToken() {
        return ResponseEntity.ok().body(userService.refreshToken());
    }
}