package br.com.genius_finance.service;

import br.com.genius_finance.model.dto.transaction.TransactionRequestDTO;
import br.com.genius_finance.model.dto.transaction.TransactionResponseDTO;
import br.com.genius_finance.model.entity.TransactionEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void prePersist(TransactionEntity transactionEntity) {
        personAssociation(transactionEntity);
        super.prePersist(transactionEntity);
    }

    private void personAssociation(TransactionEntity transactionEntity) {
        var person = personService.findByUuid(transactionEntity.getPerson().getUuid());
        transactionEntity.setPerson(person);
    }
}
