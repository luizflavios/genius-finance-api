package br.com.genius_finance.service;

import br.com.genius_finance.core.utils.AuthUtils;
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
    public void detachedAssociations(IncomeEntity incomeEntity) {
        categoryAssociation(incomeEntity);
        transactionAssociation(incomeEntity);
        personAssociation(incomeEntity);
    }

    private void categoryAssociation(IncomeEntity incomeEntity) {
        var category = categoryService.findByUuid(incomeEntity.getCategory().getUuid());
        incomeEntity.setCategory(category);
    }

    private void transactionAssociation(IncomeEntity incomeEntity) {
        incomeEntity.getTransactions().replaceAll(entity -> transactionService.findByUuid(entity.getUuid()));
    }

    private void personAssociation(IncomeEntity incomeEntity) {
        var person = personService.findByUuid(incomeEntity.getOwner().getUuid());
        incomeEntity.setOwner(person);

        var createdBy = personService.findByLoggedUser(AuthUtils.loggedUserReference());
        incomeEntity.setCreatedBy(createdBy);
    }
}
