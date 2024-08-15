package br.com.genius_finance.repository;

import br.com.genius_finance.model.entity.UserEntity;
import br.com.genius_finance.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {
    Optional<UserEntity> findByUuid(UUID uuid);
}
