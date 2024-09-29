package br.com.genius_finance.model.dto.group;

import br.com.genius_finance.model.dto.base.BaseResponseDTO;
import br.com.genius_finance.model.dto.person.SummaryPersonResponseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class GroupResponseDTO extends BaseResponseDTO {
    private String description;
    private List<SummaryPersonResponseDTO> people;
}
