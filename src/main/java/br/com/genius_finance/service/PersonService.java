package br.com.genius_finance.service;

import br.com.genius_finance.model.dto.person.PersonRequestDTO;
import br.com.genius_finance.model.dto.person.PersonResponseDTO;
import br.com.genius_finance.model.entity.PersonEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class PersonService extends BaseServiceImpl<PersonRequestDTO, PersonResponseDTO, PersonEntity> {

    private final UserService userService;

    @Autowired
    public PersonService(BaseRepository<PersonEntity> baseRepository,
                         BaseMapper<PersonRequestDTO, PersonResponseDTO, PersonEntity> baseMapper, UserService userService) {
        super(baseRepository, baseMapper);
        this.userService = userService;
    }

    @Override
    public void postPersist(PersonEntity personEntity) {
        try {
            userService.createUser(personEntity);
        } catch (Exception e) {
            log.info("Error when create user - Error: {}", e.getMessage());
            delete(personEntity.getUuid());
            throw e;
        }
    }

    public PersonEntity findByLoggedUser(UUID uuid) {
        return userService.findByUUID(uuid).getPerson();
    }

}
