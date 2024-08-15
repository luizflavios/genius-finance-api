package br.com.genius_finance.controller;

import br.com.genius_finance.controller.base.BaseControllerImpl;
import br.com.genius_finance.model.dto.group.GroupRequestDTO;
import br.com.genius_finance.model.dto.group.GroupResponseDTO;
import br.com.genius_finance.model.entity.GroupEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.service.base.BaseServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
@Tag(name = "Group")
public class GroupController extends BaseControllerImpl<GroupRequestDTO, GroupResponseDTO, GroupEntity> {

    @Autowired
    public GroupController(BaseServiceImpl<GroupRequestDTO, GroupResponseDTO, GroupEntity> baseService,
                           BaseMapper<GroupRequestDTO, GroupResponseDTO, GroupEntity> baseMapper) {
        super(baseService, baseMapper);
    }

}
