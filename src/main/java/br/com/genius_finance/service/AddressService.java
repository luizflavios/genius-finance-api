package br.com.genius_finance.service;

import br.com.genius_finance.model.dto.address.AddressRequestDTO;
import br.com.genius_finance.model.dto.address.AddressResponseDTO;
import br.com.genius_finance.model.entity.AddressEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends BaseServiceImpl<AddressRequestDTO, AddressResponseDTO, AddressEntity> {

    @Autowired
    public AddressService(BaseRepository<AddressEntity> baseRepository,
                          BaseMapper<AddressRequestDTO, AddressResponseDTO, AddressEntity> baseMapper) {
        super(baseRepository, baseMapper);
    }
}
