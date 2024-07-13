package br.com.genius_finance.controller;

import br.com.genius_finance.controller.base.BaseControllerImpl;
import br.com.genius_finance.model.dto.address.AddressRequestDTO;
import br.com.genius_finance.model.dto.address.AddressResponseDTO;
import br.com.genius_finance.model.entity.AddressEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.service.base.BaseServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
@Tag(name = "Addresses")
public class AddressController extends BaseControllerImpl<AddressRequestDTO, AddressResponseDTO, AddressEntity> {

    @Autowired
    public AddressController(BaseServiceImpl<AddressRequestDTO, AddressResponseDTO, AddressEntity> baseService,
                             BaseMapper<AddressRequestDTO, AddressResponseDTO, AddressEntity> baseMapper) {
        super(baseService, baseMapper);
    }

}
