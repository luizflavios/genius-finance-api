package br.com.genius_finance.model.dto.budget;

import br.com.genius_finance.model.dto.base.BaseResponseDTO;
import br.com.genius_finance.model.dto.expense.ExpenseResponseDTO;
import br.com.genius_finance.model.dto.group.GroupResponseDTO;
import br.com.genius_finance.model.dto.person.PersonResponseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class BudgetResponseDTO extends BaseResponseDTO {
    private String description;
    private Boolean active;
    private BigDecimal spendingLimit;
    private List<ExpenseResponseDTO> expenses;
    private GroupResponseDTO ownerGroup;
    private PersonResponseDTO owner;
}
