package br.com.genius_finance.service;

import br.com.genius_finance.model.dto.phone.PhoneRequestDTO;
import br.com.genius_finance.model.dto.phone.PhoneResponseDTO;
import br.com.genius_finance.model.entity.PhoneEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService extends BaseServiceImpl<PhoneRequestDTO, PhoneResponseDTO, PhoneEntity> {

    private final PersonService personService;

    @Autowired
    public PhoneService(BaseRepository<PhoneEntity> baseRepository,
                        BaseMapper<PhoneRequestDTO, PhoneResponseDTO, PhoneEntity> baseMapper,
                        PersonService personService) {
        super(baseRepository, baseMapper);
        this.personService = personService;
    }

    @Override
    public void prePersist(PhoneEntity phoneEntity) {
        personAssociation(phoneEntity);
        super.prePersist(phoneEntity);
    }

    private void personAssociation(PhoneEntity entity) {
        var person = personService.findByUuid(entity.getPerson().getUuid());
        entity.setPerson(person);
    }
}
