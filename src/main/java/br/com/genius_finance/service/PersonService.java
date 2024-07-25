package br.com.genius_finance.service;

import br.com.genius_finance.core.config.KeycloakConfiguration;
import br.com.genius_finance.core.utils.KeycloakUtils;
import br.com.genius_finance.model.dto.person.PersonRequestDTO;
import br.com.genius_finance.model.dto.person.PersonResponseDTO;
import br.com.genius_finance.model.entity.PersonEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class PersonService extends BaseServiceImpl<PersonRequestDTO, PersonResponseDTO, PersonEntity> {

    private final KeycloakConfiguration keycloakConfiguration;
    private final Keycloak keycloak;

    @Autowired
    public PersonService(BaseRepository<PersonEntity> baseRepository,
                         BaseMapper<PersonRequestDTO, PersonResponseDTO, PersonEntity> baseMapper, KeycloakConfiguration keycloakConfiguration, Keycloak keycloak) {
        super(baseRepository, baseMapper);
        this.keycloakConfiguration = keycloakConfiguration;
        this.keycloak = keycloak;
    }

    private static UserRepresentation getUserRepresentation(PersonEntity personEntity) {
        var credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue("admin");

        var user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(personEntity.getEmail());

        var nameSplit = personEntity.getFullName().split(" ");

        user.setFirstName(nameSplit[0]);
        user.setLastName(nameSplit[nameSplit.length - 1]);
        user.setEmail(personEntity.getEmail());
        user.setEmailVerified(true);
        user.setCredentials(Collections.singletonList(credential));
        user.setRealmRoles(Collections.singletonList("ADMIN"));

        return user;
    }

    @Override
    public void prePersist(PersonEntity personEntity) {
        super.prePersist(personEntity);
        createKeycloakUser(personEntity);
    }

    private void createKeycloakUser(PersonEntity personEntity) {
        var user = getUserRepresentation(personEntity);

        var response = keycloak
                .realm(keycloakConfiguration.getRealm())
                .users()
                .create(user);

        //TODO: CONTINUE
        if (!HttpStatus.valueOf(response.getStatus()).is2xxSuccessful()) {
            throw new RuntimeException("erro ao criar usuario");
        }

        var location = response.getHeaderString("Location");
        personEntity.setUuid(UUID.fromString(KeycloakUtils.extractUserIdFromLocation(location)));

    }
}
