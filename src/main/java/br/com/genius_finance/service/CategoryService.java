package br.com.genius_finance.service;

import br.com.genius_finance.core.utils.AuthUtils;
import br.com.genius_finance.model.dto.category.CategoryRequestDTO;
import br.com.genius_finance.model.dto.category.CategoryResponseDTO;
import br.com.genius_finance.model.entity.CategoryEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseServiceImpl<CategoryRequestDTO, CategoryResponseDTO, CategoryEntity> {

    private final PersonService personService;

    @Autowired
    public CategoryService(BaseRepository<CategoryEntity> baseRepository,
                           BaseMapper<CategoryRequestDTO, CategoryResponseDTO, CategoryEntity> baseMapper,
                           PersonService personService) {
        super(baseRepository, baseMapper);
        this.personService = personService;
    }

    @Override
    public void prePersist(CategoryEntity categoryEntity) {
        personAssociation(categoryEntity);
        super.prePersist(categoryEntity);
    }

    private void personAssociation(CategoryEntity categoryEntity) {
        var person = personService.findByLoggedUser(AuthUtils.loggedUserReference());
        categoryEntity.setCreatedBy(person);
    }
}
