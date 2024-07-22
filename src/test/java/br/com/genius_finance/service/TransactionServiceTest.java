package br.com.genius_finance.service;

import br.com.genius_finance.model.entity.PersonEntity;
import br.com.genius_finance.model.entity.TransactionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private PersonService personService;

    @Test
    void prePersist() {
        var entity = Mockito.mock(TransactionEntity.class);
        var personEntity = Mockito.mock(PersonEntity.class);

        Mockito.when(personEntity.getUuid()).thenReturn(UUID.randomUUID());
        Mockito.when(entity.getPerson()).thenReturn(personEntity);

        Mockito.when(personService.findByUuid(entity.getPerson().getUuid())).thenReturn(personEntity);

        transactionService.prePersist(entity);

        Assertions.assertAll(
                () -> Mockito.verify(personService, Mockito.times(1)).findByUuid(entity.getPerson().getUuid()),
                () -> Assertions.assertNotNull(entity.getPerson())
        );
    }
}