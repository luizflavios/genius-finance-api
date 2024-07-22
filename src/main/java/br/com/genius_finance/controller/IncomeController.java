package br.com.genius_finance.controller;

import br.com.genius_finance.controller.base.BaseControllerImpl;
import br.com.genius_finance.model.dto.income.IncomeRequestDTO;
import br.com.genius_finance.model.dto.income.IncomeResponseDTO;
import br.com.genius_finance.model.entity.IncomeEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.service.base.BaseServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/incomes")
@Tag(name = "Income")
public class IncomeController extends BaseControllerImpl<IncomeRequestDTO, IncomeResponseDTO, IncomeEntity> {

    @Autowired
    public IncomeController(BaseServiceImpl<IncomeRequestDTO, IncomeResponseDTO, IncomeEntity> baseService,
                            BaseMapper<IncomeRequestDTO, IncomeResponseDTO, IncomeEntity> baseMapper) {
        super(baseService, baseMapper);
    }

}
