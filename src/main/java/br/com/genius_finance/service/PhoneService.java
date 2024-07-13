package br.com.genius_finance.service;

import br.com.genius_finance.model.dto.phone.PhoneRequestDTO;
import br.com.genius_finance.model.dto.phone.PhoneResponseDTO;
import br.com.genius_finance.model.entity.PhoneEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.repository.base.BaseRepository;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService extends BaseServiceImpl<PhoneRequestDTO, PhoneResponseDTO, PhoneEntity> {

    @Autowired
    public PhoneService(BaseRepository<PhoneEntity> baseRepository,
                        BaseMapper<PhoneRequestDTO, PhoneResponseDTO, PhoneEntity> baseMapper) {
        super(baseRepository, baseMapper);
    }
}
