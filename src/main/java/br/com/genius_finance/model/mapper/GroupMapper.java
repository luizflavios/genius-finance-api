package br.com.genius_finance.model.mapper;

import br.com.genius_finance.model.dto.group.GroupRequestDTO;
import br.com.genius_finance.model.dto.group.GroupResponseDTO;
import br.com.genius_finance.model.entity.GroupEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper extends BaseMapper<GroupRequestDTO, GroupResponseDTO, GroupEntity> {

}
