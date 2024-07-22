package br.com.genius_finance.service;

import br.com.genius_finance.model.dto.transaction.TransactionRequestDTO;
import br.com.genius_finance.model.dto.transaction.TransactionResponseDTO;
import br.com.genius_finance.model.entity.PersonEntity;
import br.com.genius_finance.model.entity.TransactionEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
public class TransactionService extends BaseServiceImpl<TransactionRequestDTO, TransactionResponseDTO, TransactionEntity> {

    private final PersonService personService;

    @Autowired
    public TransactionService(BaseRepository<TransactionEntity> baseRepository,
                              BaseMapper<TransactionRequestDTO, TransactionResponseDTO, TransactionEntity> baseMapper,
                              PersonService personService) {
        super(baseRepository, baseMapper);
        this.personService = personService;
    }

    @Override
    public void detachedAssociations(TransactionEntity transactionEntity) {
        super.detachedAssociations(transactionEntity);
        personAssociation(transactionEntity);
    }

    private void personAssociation(TransactionEntity transactionEntity) {
        //TODO: Change this when authentication/authorization is ready
        var createdBy = personService.findByUuid(UUID.fromString("cd1f1e98-878b-464d-a168-fcbc4a9861cb"));
        transactionEntity.setCreatedBy(createdBy);

        PersonEntity owner;

        if (nonNull(transactionEntity.getOwner())) {
            owner = personService.findByUuid(transactionEntity.getOwner().getUuid());
        } else {
            owner = createdBy;
        }

        transactionEntity.setOwner(owner);
    }
}
