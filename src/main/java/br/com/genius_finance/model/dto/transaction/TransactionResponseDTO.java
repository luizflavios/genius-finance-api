package br.com.genius_finance.model.dto.transaction;

import br.com.genius_finance.core.enums.PaymentType;
import br.com.genius_finance.model.dto.base.BaseResponseDTO;
import br.com.genius_finance.model.dto.person.PersonResponseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class TransactionResponseDTO extends BaseResponseDTO implements Serializable {

    private String description;
    private BigDecimal value;
    private PaymentType paymentType;
    private PersonResponseDTO owner;

}
