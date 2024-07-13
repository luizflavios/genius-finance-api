package br.com.genius_finance.controller;

import br.com.genius_finance.controller.base.BaseControllerImpl;
import br.com.genius_finance.model.dto.phone.PhoneRequestDTO;
import br.com.genius_finance.model.dto.phone.PhoneResponseDTO;
import br.com.genius_finance.model.entity.PhoneEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.service.base.BaseServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phones")
@Tag(name = "Phones")
public class PhoneController extends BaseControllerImpl<PhoneRequestDTO, PhoneResponseDTO, PhoneEntity> {

    @Autowired
    public PhoneController(BaseServiceImpl<PhoneRequestDTO, PhoneResponseDTO, PhoneEntity> baseService,
                           BaseMapper<PhoneRequestDTO, PhoneResponseDTO, PhoneEntity> baseMapper) {
        super(baseService, baseMapper);
    }

}
