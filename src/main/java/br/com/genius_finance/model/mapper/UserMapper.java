package br.com.genius_finance.model.mapper;

import br.com.genius_finance.model.entity.PersonEntity;
import br.com.genius_finance.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", source = "keycloakId")
    @Mapping(target = "person", source = "person")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    UserEntity toEntity(UUID keycloakId, PersonEntity person);

}
