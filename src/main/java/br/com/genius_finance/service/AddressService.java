package br.com.genius_finance.service;

import br.com.genius_finance.model.dto.address.AddressRequestDTO;
import br.com.genius_finance.model.dto.address.AddressResponseDTO;
import br.com.genius_finance.model.entity.AddressEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends BaseServiceImpl<AddressRequestDTO, AddressResponseDTO, AddressEntity> {

    private final PersonService personService;

    @Autowired
    public AddressService(BaseRepository<AddressEntity> baseRepository,
                          BaseMapper<AddressRequestDTO, AddressResponseDTO, AddressEntity> baseMapper,
                          PersonService personService) {
        super(baseRepository, baseMapper);
        this.personService = personService;
    }

    @Override
    public void detachedAssociations(AddressEntity entity) {
        super.detachedAssociations(entity);
        personAssociation(entity);
    }

    private void personAssociation(AddressEntity entity) {
        var person = personService.findByUuid(entity.getPerson().getUuid());
        entity.setPerson(person);
    }

}
