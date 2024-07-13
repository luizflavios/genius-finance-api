package br.com.genius_finance.controller;

import br.com.genius_finance.controller.base.BaseControllerImpl;
import br.com.genius_finance.model.dto.transaction.TransactionRequestDTO;
import br.com.genius_finance.model.dto.transaction.TransactionResponseDTO;
import br.com.genius_finance.model.entity.TransactionEntity;
import br.com.genius_finance.model.mapper.base.BaseMapper;
import br.com.genius_finance.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController extends BaseControllerImpl<TransactionRequestDTO, TransactionResponseDTO, TransactionEntity> {

    @Autowired
    public TransactionController(BaseServiceImpl<TransactionRequestDTO, TransactionResponseDTO, TransactionEntity> baseService,
                                 BaseMapper<TransactionRequestDTO, TransactionResponseDTO, TransactionEntity> baseMapper) {
        super(baseService, baseMapper);
    }

}
