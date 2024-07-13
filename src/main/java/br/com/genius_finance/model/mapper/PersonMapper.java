package br.com.genius_finance.model.mapper;

import br.com.genius_finance.model.dto.person.PersonRequestDTO;
import br.com.genius_finance.model.dto.person.PersonResponseDTO;
import br.com.genius_finance.model.entity.PersonEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper extends BaseMapper<PersonRequestDTO, PersonResponseDTO, PersonEntity> {
}
