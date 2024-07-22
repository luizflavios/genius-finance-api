package br.com.genius_finance.service;

import br.com.genius_finance.core.enums.TransactionType;
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
    public void prePersist(ExpenseEntity expenseEntity) {
        categoryAssociation(expenseEntity);
        verifyIncomeTransactions(expenseEntity);
        personAssociation(expenseEntity);
        super.prePersist(expenseEntity);
    }

    private void categoryAssociation(ExpenseEntity expenseEntity) {
        var category = categoryService.findByUuid(expenseEntity.getCategory().getUuid());
        expenseEntity.setCategory(category);
    }

    private void verifyIncomeTransactions(ExpenseEntity expenseEntity) {
        for (int i = 0; i < expenseEntity.getTransactions().size(); i++) {
            var uuid = expenseEntity.getTransactions().get(i).getUuid();
            var transactionEntity = transactionService.findByUuid(uuid);

            if (transactionEntity.getTransactionType().equals(TransactionType.CREDIT)) {
                throw new IllegalStateException("Expense transactions can´t be debit type");
            }

            expenseEntity.getTransactions().set(i, transactionEntity);
        }
    }

    private void personAssociation(ExpenseEntity expenseEntity) {
        var person = personService.findByUuid(expenseEntity.getPerson().getUuid());
        expenseEntity.setPerson(person);
    }
}
