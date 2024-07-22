package br.com.genius_finance.controller;

import br.com.genius_finance.controller.base.BaseControllerImpl;
import br.com.genius_finance.model.dto.expense.ExpenseRequestDTO;
import br.com.genius_finance.model.dto.expense.ExpenseResponseDTO;
import br.com.genius_finance.model.entity.ExpenseEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.service.base.BaseServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
@Tag(name = "Expense")
public class ExpenseController extends BaseControllerImpl<ExpenseRequestDTO, ExpenseResponseDTO, ExpenseEntity> {

    @Autowired
    public ExpenseController(BaseServiceImpl<ExpenseRequestDTO, ExpenseResponseDTO, ExpenseEntity> baseService,
                             BaseMapper<ExpenseRequestDTO, ExpenseResponseDTO, ExpenseEntity> baseMapper) {
        super(baseService, baseMapper);
    }

}
