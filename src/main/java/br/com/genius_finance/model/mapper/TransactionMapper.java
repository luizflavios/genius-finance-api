package br.com.genius_finance.model.mapper;

import br.com.genius_finance.model.dto.transaction.TransactionRequestDTO;
import br.com.genius_finance.model.dto.transaction.TransactionResponseDTO;
import br.com.genius_finance.model.entity.TransactionEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends BaseMapper<TransactionRequestDTO, TransactionResponseDTO, TransactionEntity> {
}
