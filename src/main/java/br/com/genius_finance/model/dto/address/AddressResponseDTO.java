package br.com.genius_finance.model.dto.address;

import br.com.genius_finance.model.dto.base.BaseResponseDTO;
import br.com.genius_finance.model.dto.person.SummaryPersonResponseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AddressResponseDTO extends BaseResponseDTO implements Serializable {

    private String address;
    private String number;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String complement;
    private String district;
    private SummaryPersonResponseDTO person;

}
