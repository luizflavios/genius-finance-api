package br.com.genius_finance.model.dto.transaction;

import br.com.genius_finance.core.enums.PaymentType;
import br.com.genius_finance.model.dto.base.BaseDTO;
import br.com.genius_finance.model.dto.base.BaseEntityDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class TransactionRequestDTO implements BaseDTO {

    private BaseEntityDTO owner;

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal value;

    @NotNull
    private PaymentType paymentType;
}
