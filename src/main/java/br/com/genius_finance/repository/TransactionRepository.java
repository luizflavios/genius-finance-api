package br.com.genius_finance.repository;

import br.com.genius_finance.model.entity.TransactionEntity;
import br.com.genius_finance.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends BaseRepository<TransactionEntity> {
}
