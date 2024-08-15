package br.com.genius_finance.model.dto.group;

import br.com.genius_finance.model.dto.base.BaseDTO;
import br.com.genius_finance.model.dto.base.BaseEntityDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class GroupRequestDTO implements BaseDTO {

    @NotBlank
    private String description;

    private List<BaseEntityDTO> people;

}
