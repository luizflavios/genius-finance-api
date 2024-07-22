package br.com.genius_finance.model.mapper;

import br.com.genius_finance.core.utils.TransactionUtils;
import br.com.genius_finance.model.dto.income.IncomeRequestDTO;
import br.com.genius_finance.model.dto.income.IncomeResponseDTO;
import br.com.genius_finance.model.entity.IncomeEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {TransactionUtils.class})
public interface IncomeMapper extends BaseMapper<IncomeRequestDTO, IncomeResponseDTO, IncomeEntity> {

    @Override
    @Mapping(target = "total", expression = "java(TransactionUtils.transactionsSum(entity.getTransactions()))")
    IncomeResponseDTO toDTO(IncomeEntity entity);
}
