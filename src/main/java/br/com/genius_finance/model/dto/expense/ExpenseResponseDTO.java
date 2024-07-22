package br.com.genius_finance.model.dto.expense;

import br.com.genius_finance.model.dto.base.BaseResponseDTO;
import br.com.genius_finance.model.dto.category.CategoryResponseDTO;
import br.com.genius_finance.model.dto.person.PersonResponseDTO;
import br.com.genius_finance.model.dto.transaction.TransactionResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ExpenseResponseDTO extends BaseResponseDTO {
    private String description;

    private CategoryResponseDTO category;

    @JsonIgnoreProperties("person")
    private List<TransactionResponseDTO> transactions;

    private PersonResponseDTO person;
}
