package br.com.genius_finance.controller;

import br.com.genius_finance.controller.base.BaseControllerImpl;
import br.com.genius_finance.model.dto.budget.BudgetRequestDTO;
import br.com.genius_finance.model.dto.budget.BudgetResponseDTO;
import br.com.genius_finance.model.entity.BudgetEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.service.base.BaseServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budgets")
@Tag(name = "Budget")
public class BudgetController extends BaseControllerImpl<BudgetRequestDTO, BudgetResponseDTO, BudgetEntity> {

    @Autowired
    public BudgetController(BaseServiceImpl<BudgetRequestDTO, BudgetResponseDTO, BudgetEntity> baseService,
                            BaseMapper<BudgetRequestDTO, BudgetResponseDTO, BudgetEntity> baseMapper) {
        super(baseService, baseMapper);
    }

}
