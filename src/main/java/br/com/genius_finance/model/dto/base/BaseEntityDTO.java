package br.com.genius_finance.model.dto.base;


import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BaseEntityDTO {
    private UUID uuid;
}
