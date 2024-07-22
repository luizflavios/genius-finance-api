package br.com.genius_finance.model.mapper;

import br.com.genius_finance.model.dto.category.CategoryRequestDTO;
import br.com.genius_finance.model.dto.category.CategoryResponseDTO;
import br.com.genius_finance.model.entity.CategoryEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryRequestDTO, CategoryResponseDTO, CategoryEntity> {
}
