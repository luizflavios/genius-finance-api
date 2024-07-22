package br.com.genius_finance.model.dto.base;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ErrorDTO {
    private String error;
    private String detail;
    private Long timestamp = System.currentTimeMillis();
}
