package br.com.genius_finance.model.mapper;

import br.com.genius_finance.model.dto.budget.BudgetRequestDTO;
import br.com.genius_finance.model.dto.budget.BudgetResponseDTO;
import br.com.genius_finance.model.entity.BudgetEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetMapper extends BaseMapper<BudgetRequestDTO, BudgetResponseDTO, BudgetEntity> {

}
