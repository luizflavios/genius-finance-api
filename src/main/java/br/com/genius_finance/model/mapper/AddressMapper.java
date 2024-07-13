package br.com.genius_finance.model.mapper;

import br.com.genius_finance.model.dto.address.AddressRequestDTO;
import br.com.genius_finance.model.dto.address.AddressResponseDTO;
import br.com.genius_finance.model.entity.AddressEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends BaseMapper<AddressRequestDTO, AddressResponseDTO, AddressEntity> {
}
