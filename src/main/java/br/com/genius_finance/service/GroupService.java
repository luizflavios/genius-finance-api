package br.com.genius_finance.service;

import br.com.genius_finance.model.dto.group.GroupRequestDTO;
import br.com.genius_finance.model.dto.group.GroupResponseDTO;
import br.com.genius_finance.model.entity.GroupEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends BaseServiceImpl<GroupRequestDTO, GroupResponseDTO, GroupEntity> {

    private final PersonService personService;

    public GroupService(BaseRepository<GroupEntity> baseRepository,
                        BaseMapper<GroupRequestDTO, GroupResponseDTO, GroupEntity> baseMapper, PersonService personService) {
        super(baseRepository, baseMapper);
        this.personService = personService;
    }

    @Override
    public void detachedAssociations(GroupEntity groupEntity) {
        groupEntity.getPeople().replaceAll(personEntity -> personService.findByUuid(personEntity.getUuid()));
    }
}
