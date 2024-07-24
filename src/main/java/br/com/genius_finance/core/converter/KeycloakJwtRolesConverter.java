package br.com.genius_finance.core.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Component
public class KeycloakJwtRolesConverter implements Converter<Jwt, JwtAuthenticationToken> {

    public static final String ROLE_PREFIX = "ROLE";
    private static final String CLAIM_REALM_ACCESS = "realm_access";
    private static final String CLAIM_RESOURCE_ACCESS = "resource_access";
    private static final String CLAIM_ROLES = "roles";
    @Value("${keycloak.client-id}")
    private String kcClientId;

    @Override
    public JwtAuthenticationToken convert(Jwt jwt) {

        Map<String, Collection<String>> realmAccess = jwt.getClaim(CLAIM_REALM_ACCESS);
        Map<String, Map<String, Collection<String>>> resourceAccess = jwt.getClaim(CLAIM_RESOURCE_ACCESS);

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (realmAccess != null && !realmAccess.isEmpty()) {
            Collection<String> realmRole = realmAccess.get(CLAIM_ROLES);
            if (realmRole != null && !realmRole.isEmpty()) {
                realmRole.forEach(r -> {
                    if (resourceAccess != null && !resourceAccess.isEmpty() && resourceAccess.containsKey(kcClientId)) {
                        resourceAccess.get(kcClientId).get(CLAIM_ROLES).forEach(resourceRole -> {
                            String role = String.format("%s_%s", r, resourceRole).toUpperCase(Locale.ROOT);
                            grantedAuthorities.add(new SimpleGrantedAuthority(role));
                        });
                    } else {
                        var containsPrefix = r.contains(ROLE_PREFIX);
                        grantedAuthorities.add(containsPrefix ?
                                new SimpleGrantedAuthority(r) :
                                new SimpleGrantedAuthority(String.format("%s_%s", ROLE_PREFIX, r)));
                    }
                });
            }
        }

        return new JwtAuthenticationToken(jwt, grantedAuthorities);
    }
}