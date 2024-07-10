package br.com.genius_finance.service;

import br.com.genius_finance.model.entity.BaseEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BaseService<E extends BaseEntity> {

    E save(@NotNull E e);

    E findByUuid(@NotNull UUID uuid);

    Page<E> findAll(@NotNull Pageable pageable);

    E update(@NotNull UUID uuid, @NotNull E e);

    void delete(@NotNull UUID uuid);

}
