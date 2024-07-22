package br.com.genius_finance.repository;

import br.com.genius_finance.model.entity.PersonEntity;
import br.com.genius_finance.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseRepository<PersonEntity> {
}
