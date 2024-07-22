package br.com.genius_finance.service.base;

import br.com.genius_finance.model.dto.base.BaseDTO;
import br.com.genius_finance.model.dto.base.BaseResponseDTO;
import br.com.genius_finance.model.entity.base.BaseEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BaseService<T extends BaseDTO, D extends BaseResponseDTO, E extends BaseEntity> {

    E save(@NotNull T t);

    E findByUuid(@NotNull UUID uuid);

    Page<D> findAll(@NotNull Pageable pageable);

    E update(@NotNull UUID uuid, @NotNull T t);

    void delete(@NotNull UUID uuid);

}
