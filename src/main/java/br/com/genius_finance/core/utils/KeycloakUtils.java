package br.com.genius_finance.core.utils;

import br.com.genius_finance.model.entity.PersonEntity;
import jakarta.ws.rs.core.Response;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.UUID;

import static br.com.genius_finance.core.constants.AppConstants.SPACE;

@UtilityClass
@Slf4j
public class KeycloakUtils {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_";
    private static final int PASSWORD_LENGTH = 12;
    private static final String LOCATION = "Location";
    private static final String REPLACE_POSITION = "$1";
    private static final String USER_GROUP = "Users";

    public static UUID extractUserIdFromLocation(Response response) {
        var location = response.getHeaderString(LOCATION);
        return UUID.fromString(location.replaceAll(".*/(.*)$", REPLACE_POSITION));
    }

    public static UserRepresentation buildUserRepresentationFromPerson(PersonEntity personEntity) {
        var credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(randomPassword());

        var user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(personEntity.getEmail());

        var nameSplit = personEntity.getFullName().split(SPACE);

        user.setFirstName(nameSplit[0]);
        user.setLastName(nameSplit[nameSplit.length - 1]);
        user.setEmail(personEntity.getEmail());
        user.setEmailVerified(true);
        user.setCredentials(Collections.singletonList(credential));
        user.setGroups(Collections.singletonList(USER_GROUP));

        return user;
    }

    public static String randomPassword() {
        var random = new SecureRandom();
        var password = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        log.warn(password.toString());
        return password.toString();
    }
}
