package br.com.genius_finance.service;

import br.com.genius_finance.core.enums.TransactionType;
import br.com.genius_finance.model.dto.income.IncomeRequestDTO;
import br.com.genius_finance.model.dto.income.IncomeResponseDTO;
import br.com.genius_finance.model.entity.IncomeEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeService extends BaseServiceImpl<IncomeRequestDTO, IncomeResponseDTO, IncomeEntity> {

    private final PersonService personService;
    private final TransactionService transactionService;
    private final CategoryService categoryService;

    @Autowired
    public IncomeService(BaseRepository<IncomeEntity> baseRepository,
                         BaseMapper<IncomeRequestDTO, IncomeResponseDTO, IncomeEntity> baseMapper,
                         PersonService personService, TransactionService transactionService,
                         CategoryService categoryService) {
        super(baseRepository, baseMapper);
        this.personService = personService;
        this.transactionService = transactionService;
        this.categoryService = categoryService;
    }

    @Override
    public void prePersist(IncomeEntity incomeEntity) {
        categoryAssociation(incomeEntity);
        verifyIncomeTransactions(incomeEntity);
        personAssociation(incomeEntity);
        super.prePersist(incomeEntity);
    }

    private void categoryAssociation(IncomeEntity incomeEntity) {
        var category = categoryService.findByUuid(incomeEntity.getCategory().getUuid());
        incomeEntity.setCategory(category);
    }

    private void verifyIncomeTransactions(IncomeEntity incomeEntity) {
        for (int i = 0; i < incomeEntity.getTransactions().size(); i++) {
            var uuid = incomeEntity.getTransactions().get(i).getUuid();
            var transactionEntity = transactionService.findByUuid(uuid);

            if (transactionEntity.getTransactionType().equals(TransactionType.DEBIT)) {
                throw new IllegalStateException("Income transactions can´t be debit type");
            }

            incomeEntity.getTransactions().set(i, transactionEntity);
        }
    }

    private void personAssociation(IncomeEntity incomeEntity) {
        var person = personService.findByUuid(incomeEntity.getPerson().getUuid());
        incomeEntity.setPerson(person);
    }
}
