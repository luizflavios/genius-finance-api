package br.com.genius_finance.model.dto.phone;

import br.com.genius_finance.model.dto.base.BaseDTO;
import br.com.genius_finance.model.dto.base.BaseEntityDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class PhoneRequestDTO implements BaseDTO {

    @NotNull
    private BaseEntityDTO person;

    @NotBlank
    private String countryCode;

    @NotBlank
    private String cityCode;

    @NotBlank
    private String phoneNumber;

}
