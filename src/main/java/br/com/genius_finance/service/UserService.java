package br.com.genius_finance.service;

import br.com.genius_finance.client.KeycloakClient;
import br.com.genius_finance.model.dto.base.LoginRequestDTO;
import br.com.genius_finance.model.dto.base.TokenDTO;
import br.com.genius_finance.model.entity.PersonEntity;
import br.com.genius_finance.model.entity.UserEntity;
import br.com.genius_finance.model.mapper.UserMapper;
import br.com.genius_finance.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final KeycloakClient keycloakClient;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(KeycloakClient keycloakClient, UserRepository userRepository, UserMapper userMapper) {
        this.keycloakClient = keycloakClient;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public TokenDTO login(LoginRequestDTO loginRequestDTO) {
        var tokenDTO = keycloakClient.login(loginRequestDTO);
        updateLastLogin(loginRequestDTO.getUsername());
        return tokenDTO;
    }

    private void updateLastLogin(String username) {
        var user = userRepository.findByPersonEmail(username).orElseThrow(EntityNotFoundException::new);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }

    public TokenDTO refreshToken() {
        return keycloakClient.refreshToken();
    }

    public void createUser(PersonEntity personEntity) {
        var keycloakUserId = keycloakClient.createKeycloakUser(personEntity);
        var user = userMapper.toEntity(keycloakUserId, personEntity);
        userRepository.save(user);
    }

    public UserEntity findByUUID(UUID uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
    }
}
