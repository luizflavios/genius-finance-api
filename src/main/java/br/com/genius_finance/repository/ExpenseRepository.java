package br.com.genius_finance.repository;

import br.com.genius_finance.model.entity.ExpenseEntity;
import br.com.genius_finance.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends BaseRepository<ExpenseEntity> {
}
