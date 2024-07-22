package br.com.genius_finance.model.mapper;

import br.com.genius_finance.model.dto.income.IncomeRequestDTO;
import br.com.genius_finance.model.dto.income.IncomeResponseDTO;
import br.com.genius_finance.model.entity.IncomeEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncomeMapper extends BaseMapper<IncomeRequestDTO, IncomeResponseDTO, IncomeEntity> {
}
