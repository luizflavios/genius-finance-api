package br.com.genius_finance.model.dto.person;

import br.com.genius_finance.core.enums.Gender;
import br.com.genius_finance.model.dto.base.BaseResponseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class PersonResponseDTO extends BaseResponseDTO implements Serializable {

    private String fullName;

    private LocalDate birthDate;

    private Gender gender;

    private String email;

}
