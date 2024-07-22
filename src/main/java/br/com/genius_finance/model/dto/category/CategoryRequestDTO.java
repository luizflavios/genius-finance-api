package br.com.genius_finance.model.dto.category;

import br.com.genius_finance.core.enums.CategoryType;
import br.com.genius_finance.model.dto.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class CategoryRequestDTO implements BaseDTO {

    @NotBlank
    private String description;

    @NotNull
    private CategoryType type;

}
