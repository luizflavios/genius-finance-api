package br.com.genius_finance.service;

import br.com.genius_finance.model.entity.AddressEntity;
import br.com.genius_finance.model.entity.PersonEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private PersonService personService;

    @Test
    void prePersist() {
        var entity = Mockito.mock(AddressEntity.class);
        var personEntity = Mockito.mock(PersonEntity.class);

        Mockito.when(personEntity.getUuid()).thenReturn(UUID.randomUUID());
        Mockito.when(entity.getPerson()).thenReturn(personEntity);

        Mockito.when(personService.findByUuid(entity.getPerson().getUuid())).thenReturn(personEntity);

        addressService.prePersist(entity);

        Assertions.assertAll(
                () -> Mockito.verify(personService, Mockito.times(1)).findByUuid(entity.getPerson().getUuid()),
                () -> Assertions.assertNotNull(entity.getPerson())
        );
    }
}