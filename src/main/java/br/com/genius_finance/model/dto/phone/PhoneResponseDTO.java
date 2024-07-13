package br.com.genius_finance.model.dto.phone;

import br.com.genius_finance.model.dto.base.BaseResponseDTO;
import br.com.genius_finance.model.dto.person.PersonResponseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class PhoneResponseDTO extends BaseResponseDTO implements Serializable {

    private String countryCode;

    private String cityCode;

    private String phoneNumber;

    private PersonResponseDTO person;

}
