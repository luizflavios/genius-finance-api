package br.com.genius_finance.model.dto.budget;

import br.com.genius_finance.model.dto.base.BaseDTO;
import br.com.genius_finance.model.dto.base.BaseEntityDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class BudgetRequestDTO implements BaseDTO {

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal spendingLimit;

    private List<BaseEntityDTO> expenses;

    private BaseEntityDTO ownerGroup;

    private BaseEntityDTO owner;

}
