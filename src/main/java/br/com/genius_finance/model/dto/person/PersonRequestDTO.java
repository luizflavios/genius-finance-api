package br.com.genius_finance.model.dto.person;

import br.com.genius_finance.core.enums.Gender;
import br.com.genius_finance.model.dto.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class PersonRequestDTO implements BaseDTO {

    @NotBlank
    private String fullName;

    @NotBlank
    private LocalDate birthDate;

    @NotBlank
    private Gender gender;

    @NotBlank
    private String email;

}
