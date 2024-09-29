package br.com.genius_finance.model.dto.person;

import br.com.genius_finance.model.dto.base.SummaryResponseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class SummaryPersonResponseDTO extends SummaryResponseDTO implements Serializable {

    private String fullName;

}
