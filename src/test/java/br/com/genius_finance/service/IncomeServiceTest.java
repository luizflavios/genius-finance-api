package br.com.genius_finance.service;

import br.com.genius_finance.core.enums.TransactionType;
import br.com.genius_finance.model.entity.CategoryEntity;
import br.com.genius_finance.model.entity.IncomeEntity;
import br.com.genius_finance.model.entity.PersonEntity;
import br.com.genius_finance.model.entity.TransactionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class IncomeServiceTest {

    @InjectMocks
    private IncomeService incomeService;

    @Mock
    private PersonService personService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private TransactionService transactionService;

    @Test
    void prePersist() {
        var categoryEntityMock = Mockito.mock(CategoryEntity.class);
        Mockito.when(categoryEntityMock.getUuid()).thenReturn(UUID.randomUUID());

        Mockito.when(categoryService.findByUuid(categoryEntityMock.getUuid())).thenReturn(categoryEntityMock);

        var personEntityMock = Mockito.mock(PersonEntity.class);
        Mockito.when(personEntityMock.getUuid()).thenReturn(UUID.randomUUID());

        Mockito.when(personService.findByUuid(personEntityMock.getUuid())).thenReturn(personEntityMock);

        var transactionEntityMock = Mockito.mock(TransactionEntity.class);
        Mockito.when(transactionEntityMock.getUuid()).thenReturn(UUID.randomUUID());
        Mockito.when(transactionEntityMock.getTransactionType()).thenReturn(TransactionType.CREDIT);

        Mockito.when(transactionService.findByUuid(transactionEntityMock.getUuid())).thenReturn(transactionEntityMock);

        var transactionsMockList = new ArrayList<TransactionEntity>(1);
        transactionsMockList.add(transactionEntityMock);

        var incomeEntityMock = Mockito.mock(IncomeEntity.class);
        Mockito.when(incomeEntityMock.getCategory()).thenReturn(categoryEntityMock);
        Mockito.when(incomeEntityMock.getPerson()).thenReturn(personEntityMock);
        Mockito.when(incomeEntityMock.getTransactions()).thenReturn(transactionsMockList);

        incomeService.prePersist(incomeEntityMock);

        Assertions.assertAll(
                () -> Mockito.verify(categoryService, Mockito.times(1)).findByUuid(incomeEntityMock.getCategory().getUuid()),
                () -> Assertions.assertNotNull(incomeEntityMock.getCategory()),
                () -> Mockito.verify(personService, Mockito.times(1)).findByUuid(incomeEntityMock.getPerson().getUuid()),
                () -> Assertions.assertNotNull(incomeEntityMock.getPerson()),
                () -> Mockito.verify(transactionService, Mockito.times(1)).findByUuid(transactionEntityMock.getUuid()),
                () -> Assertions.assertNotNull(incomeEntityMock.getTransactions())
        );
    }

    @Test
    void throwIllegalStateExceptionWhenRunPrePersist() {
        var categoryEntityMock = Mockito.mock(CategoryEntity.class);
        Mockito.when(categoryEntityMock.getUuid()).thenReturn(UUID.randomUUID());

        Mockito.when(categoryService.findByUuid(categoryEntityMock.getUuid())).thenReturn(categoryEntityMock);

        var transactionEntityMock = Mockito.mock(TransactionEntity.class);
        Mockito.when(transactionEntityMock.getUuid()).thenReturn(UUID.randomUUID());
        Mockito.when(transactionEntityMock.getTransactionType()).thenReturn(TransactionType.DEBIT);

        Mockito.when(transactionService.findByUuid(transactionEntityMock.getUuid())).thenReturn(transactionEntityMock);

        var transactionsMockList = new ArrayList<TransactionEntity>(1);
        transactionsMockList.add(transactionEntityMock);

        var incomeEntityMock = Mockito.mock(IncomeEntity.class);
        Mockito.when(incomeEntityMock.getCategory()).thenReturn(categoryEntityMock);
        Mockito.when(incomeEntityMock.getTransactions()).thenReturn(transactionsMockList);

        Assertions.assertThrows(IllegalStateException.class, () -> incomeService.prePersist(incomeEntityMock));
    }
}