package br.com.genius_finance.core.security;

import br.com.genius_finance.core.config.KeycloakConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import static java.util.Collections.emptyList;

@Slf4j
@Component
public class KeycloakJwtRolesConverter implements Converter<Jwt, JwtAuthenticationToken> {

    public static final String ROLE_PREFIX = "ROLE";
    private static final String CLAIM_REALM_ACCESS = "realm_access";
    private static final String CLAIM_RESOURCE_ACCESS = "resource_access";
    private static final String CLAIM_ROLES = "roles";

    private final KeycloakConfiguration keycloakConfiguration;

    @Autowired
    public KeycloakJwtRolesConverter(KeycloakConfiguration keycloakConfiguration) {
        this.keycloakConfiguration = keycloakConfiguration;
    }

    @Override
    public JwtAuthenticationToken convert(Jwt jwt) {

        Map<String, Collection<String>> realmAccess = jwt.getClaim(CLAIM_REALM_ACCESS);
        Map<String, Map<String, Collection<String>>> resourceAccess = jwt.getClaim(CLAIM_RESOURCE_ACCESS);
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (realmAccess != null) {
            Collection<String> realmRoles = realmAccess.getOrDefault(CLAIM_ROLES, emptyList());
            realmRoles.forEach(r -> {
                boolean hasResourceRoles = resourceAccess != null && resourceAccess.containsKey(keycloakConfiguration.getClientId());
                if (hasResourceRoles) {
                    Collection<String> resourceRoles = resourceAccess.get(keycloakConfiguration.getClientId()).getOrDefault(CLAIM_ROLES, emptyList());
                    resourceRoles.forEach(resourceRole ->
                            grantedAuthorities.add(new SimpleGrantedAuthority(formatRole(r, resourceRole)))
                    );
                } else {
                    grantedAuthorities.add(new SimpleGrantedAuthority(formatRoleWithPrefix(r)));
                }
            });
        }

        var authentication = new JwtAuthenticationToken(jwt, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return authentication;
    }

    private String formatRole(String realmRole, String resourceRole) {
        return String.format("%s_%s", realmRole, resourceRole).toUpperCase(Locale.ROOT);
    }

    private String formatRoleWithPrefix(String role) {
        return role.contains(ROLE_PREFIX) ? role : String.format("%s_%s", ROLE_PREFIX, role);
    }
}