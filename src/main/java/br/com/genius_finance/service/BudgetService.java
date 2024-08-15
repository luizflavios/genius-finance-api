package br.com.genius_finance.service;

import br.com.genius_finance.core.utils.AuthUtils;
import br.com.genius_finance.model.dto.budget.BudgetRequestDTO;
import br.com.genius_finance.model.dto.budget.BudgetResponseDTO;
import br.com.genius_finance.model.entity.BudgetEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class BudgetService extends BaseServiceImpl<BudgetRequestDTO, BudgetResponseDTO, BudgetEntity> {

    private final ExpenseService expenseService;
    private final GroupService groupService;
    private final PersonService personService;

    public BudgetService(BaseRepository<BudgetEntity> baseRepository,
                         BaseMapper<BudgetRequestDTO, BudgetResponseDTO, BudgetEntity> baseMapper,
                         ExpenseService expenseService,
                         GroupService groupService,
                         PersonService personService) {
        super(baseRepository, baseMapper);
        this.expenseService = expenseService;
        this.groupService = groupService;
        this.personService = personService;
    }

    @Override
    public void detachedAssociations(BudgetEntity budgetEntity) {
        expenseAssociation(budgetEntity);

        if (nonNull(budgetEntity.getOwnerGroup())) {
            groupAssociation(budgetEntity);
        }

        personAssociation(budgetEntity);
    }

    private void personAssociation(BudgetEntity budgetEntity) {
        var person = personService.findByLoggedUser(AuthUtils.loggedUserReference());
        budgetEntity.setCreatedBy(person);
    }

    private void groupAssociation(BudgetEntity budgetEntity) {
        var group = groupService.findByUuid(budgetEntity.getOwnerGroup().getUuid());
        budgetEntity.setOwnerGroup(group);
    }

    private void expenseAssociation(BudgetEntity budgetEntity) {
        budgetEntity.getExpenses().replaceAll(expenseEntity -> expenseService.findByUuid(expenseEntity.getUuid()));
    }

}
