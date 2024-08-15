package br.com.genius_finance.core.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

@UtilityClass
public class AuthUtils {

    private static final String SUB = "sub";

    public static UUID loggedUserReference() {
        var authentication = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UUID.fromString(authentication.getClaim(SUB).toString());
    }

}
