package br.com.genius_finance.model.dto.phone;

import br.com.genius_finance.model.dto.base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class PhoneRequestDTO implements BaseDTO {

    @NotBlank
    private String countryCode;

    @NotBlank
    private String cityCode;

    @NotBlank
    private String phoneNumber;

}
