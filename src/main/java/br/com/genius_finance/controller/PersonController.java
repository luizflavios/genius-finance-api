package br.com.genius_finance.controller;

import br.com.genius_finance.controller.base.BaseControllerImpl;
import br.com.genius_finance.model.dto.person.PersonRequestDTO;
import br.com.genius_finance.model.dto.person.PersonResponseDTO;
import br.com.genius_finance.model.entity.PersonEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PersonController extends BaseControllerImpl<PersonRequestDTO, PersonResponseDTO, PersonEntity> {

    @Autowired
    public PersonController(BaseServiceImpl<PersonRequestDTO, PersonResponseDTO, PersonEntity> baseService,
                            BaseMapper<PersonRequestDTO, PersonResponseDTO, PersonEntity> baseMapper) {
        super(baseService, baseMapper);
    }

}
