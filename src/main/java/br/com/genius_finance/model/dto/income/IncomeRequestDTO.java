package br.com.genius_finance.model.dto.income;

import br.com.genius_finance.model.dto.base.BaseDTO;
import br.com.genius_finance.model.dto.base.BaseEntityDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class IncomeRequestDTO implements BaseDTO {

    @NotBlank
    private String description;

    @NotNull
    private BaseEntityDTO category;

    @NotEmpty
    private List<BaseEntityDTO> transactions;

    @NotNull
    private BaseEntityDTO person;

}
