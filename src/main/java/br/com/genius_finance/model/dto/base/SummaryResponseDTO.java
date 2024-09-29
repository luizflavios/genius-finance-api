package br.com.genius_finance.model.dto.base;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public abstract class SummaryResponseDTO implements BaseDTO {
    private UUID uuid;
}
