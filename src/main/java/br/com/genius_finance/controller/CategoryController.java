package br.com.genius_finance.controller;

import br.com.genius_finance.controller.base.BaseControllerImpl;
import br.com.genius_finance.model.dto.category.CategoryRequestDTO;
import br.com.genius_finance.model.dto.category.CategoryResponseDTO;
import br.com.genius_finance.model.entity.CategoryEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.service.base.BaseServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category")
public class CategoryController extends BaseControllerImpl<CategoryRequestDTO, CategoryResponseDTO, CategoryEntity> {

    @Autowired
    public CategoryController(BaseServiceImpl<CategoryRequestDTO, CategoryResponseDTO, CategoryEntity> baseService,
                              BaseMapper<CategoryRequestDTO, CategoryResponseDTO, CategoryEntity> baseMapper) {
        super(baseService, baseMapper);
    }

}
