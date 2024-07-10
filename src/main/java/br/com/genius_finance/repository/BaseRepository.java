package br.com.genius_finance.repository;

import br.com.genius_finance.model.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public abstract class BaseRepository<E extends BaseEntity> implements JpaRepository<E, Long> {

    private static final String UUID = "uuid";

    @PersistenceContext
    private final EntityManager entityManager;

    private final Class<E> domain;

    public Optional<E> findByUUID(@NotNull UUID uuid) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(domain);
        var root = criteriaQuery.from(domain);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(UUID), uuid));

        try {
            var result = entityManager.createQuery(criteriaQuery).getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }

    }

}
