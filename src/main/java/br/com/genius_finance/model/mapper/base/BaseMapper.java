package br.com.genius_finance.model.mapper.base;

import br.com.genius_finance.model.dto.base.BaseDTO;
import br.com.genius_finance.model.dto.base.BaseResponseDTO;
import br.com.genius_finance.model.entity.BaseEntity;
import org.mapstruct.MappingTarget;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseMapper<T extends BaseDTO, D extends BaseResponseDTO, E extends BaseEntity> {

    E toEntity(T requestDTO);

    D toDTO(E entity);

    void copyProperties(T requestDTO, @MappingTarget E entity);

}
