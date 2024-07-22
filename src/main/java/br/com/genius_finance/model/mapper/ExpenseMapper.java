package br.com.genius_finance.model.mapper;

import br.com.genius_finance.core.utils.TransactionUtils;
import br.com.genius_finance.model.dto.expense.ExpenseRequestDTO;
import br.com.genius_finance.model.dto.expense.ExpenseResponseDTO;
import br.com.genius_finance.model.entity.ExpenseEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {TransactionUtils.class})
public interface ExpenseMapper extends BaseMapper<ExpenseRequestDTO, ExpenseResponseDTO, ExpenseEntity> {

    @Override
    @Mapping(target = "total", expression = "java(TransactionUtils.transactionsSum(entity.getTransactions()))")
    ExpenseResponseDTO toDTO(ExpenseEntity entity);
}
