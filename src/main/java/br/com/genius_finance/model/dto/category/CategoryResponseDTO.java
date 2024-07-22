package br.com.genius_finance.model.dto.category;

import br.com.genius_finance.core.enums.CategoryType;
import br.com.genius_finance.model.dto.base.BaseResponseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CategoryResponseDTO extends BaseResponseDTO {

    private String description;

    private CategoryType type;

}
