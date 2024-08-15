package br.com.genius_finance.service;

import br.com.genius_finance.core.utils.AuthUtils;
import br.com.genius_finance.model.dto.expense.ExpenseRequestDTO;
import br.com.genius_finance.model.dto.expense.ExpenseResponseDTO;
import br.com.genius_finance.model.entity.ExpenseEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService extends BaseServiceImpl<ExpenseRequestDTO, ExpenseResponseDTO, ExpenseEntity> {

    private final PersonService personService;
    private final TransactionService transactionService;
    private final CategoryService categoryService;

    @Autowired
    public ExpenseService(BaseRepository<ExpenseEntity> baseRepository,
                          BaseMapper<ExpenseRequestDTO, ExpenseResponseDTO, ExpenseEntity> baseMapper,
                          PersonService personService, TransactionService transactionService,
                          CategoryService categoryService) {
        super(baseRepository, baseMapper);
        this.personService = personService;
        this.transactionService = transactionService;
        this.categoryService = categoryService;
    }

    @Override
    public void detachedAssociations(ExpenseEntity expense) {
        super.detachedAssociations(expense);
        categoryAssociation(expense);
        transactionAssociation(expense);
        personAssociation(expense);
    }

    private void categoryAssociation(ExpenseEntity expenseEntity) {
        var category = categoryService.findByUuid(expenseEntity.getCategory().getUuid());
        expenseEntity.setCategory(category);
    }

    private void transactionAssociation(ExpenseEntity expenseEntity) {
        expenseEntity.getTransactions().replaceAll(entity -> transactionService.findByUuid(entity.getUuid()));
    }

    private void personAssociation(ExpenseEntity expenseEntity) {
        var person = personService.findByUuid(expenseEntity.getOwner().getUuid());
        expenseEntity.setOwner(person);

        var createdBy = personService.findByLoggedUser(AuthUtils.loggedUserReference());
        expenseEntity.setCreatedBy(createdBy);
    }
}
