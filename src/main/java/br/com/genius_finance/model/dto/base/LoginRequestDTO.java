package br.com.genius_finance.model.dto.base;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class LoginRequestDTO {

    private String username;
    private String password;

}