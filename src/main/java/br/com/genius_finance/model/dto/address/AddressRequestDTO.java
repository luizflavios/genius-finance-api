package br.com.genius_finance.model.dto.address;

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
public class AddressRequestDTO implements BaseDTO {

    @NotNull
    private BaseEntityDTO person;

    @NotBlank
    private String address;

    @NotBlank
    private String number;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    private String zipCode;

    private String complement;

    private String district;

}
