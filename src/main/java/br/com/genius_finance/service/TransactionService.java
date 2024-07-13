package br.com.genius_finance.service;

import br.com.genius_finance.model.dto.transaction.TransactionRequestDTO;
import br.com.genius_finance.model.dto.transaction.TransactionResponseDTO;
import br.com.genius_finance.model.entity.TransactionEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends BaseServiceImpl<TransactionRequestDTO, TransactionResponseDTO, TransactionEntity> {

    @Autowired
    public TransactionService(BaseRepository<TransactionEntity> baseRepository,
                              BaseMapper<TransactionRequestDTO, TransactionResponseDTO, TransactionEntity> baseMapper) {
        super(baseRepository, baseMapper);
    }
}
