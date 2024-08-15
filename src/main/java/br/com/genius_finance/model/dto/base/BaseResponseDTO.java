package br.com.genius_finance.model.dto.base;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public abstract class BaseResponseDTO implements BaseDTO {
    private UUID uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
