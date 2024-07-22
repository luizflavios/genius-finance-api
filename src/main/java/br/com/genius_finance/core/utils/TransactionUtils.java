package br.com.genius_finance.core.utils;

import br.com.genius_finance.model.entity.TransactionEntity;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;

@UtilityClass
public class TransactionUtils {

    public static BigDecimal transactionsSum(List<TransactionEntity> transactionEntityList) {
        return transactionEntityList.stream()
                .map(TransactionEntity::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
