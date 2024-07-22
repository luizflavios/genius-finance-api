package br.com.genius_finance.model.mapper;

import br.com.genius_finance.model.dto.expense.ExpenseRequestDTO;
import br.com.genius_finance.model.dto.expense.ExpenseResponseDTO;
import br.com.genius_finance.model.entity.ExpenseEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper extends BaseMapper<ExpenseRequestDTO, ExpenseResponseDTO, ExpenseEntity> {
}
