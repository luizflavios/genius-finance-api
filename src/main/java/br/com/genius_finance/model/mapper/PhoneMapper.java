package br.com.genius_finance.model.mapper;

import br.com.genius_finance.model.dto.phone.PhoneRequestDTO;
import br.com.genius_finance.model.dto.phone.PhoneResponseDTO;
import br.com.genius_finance.model.entity.PhoneEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneMapper extends BaseMapper<PhoneRequestDTO, PhoneResponseDTO, PhoneEntity> {
}
