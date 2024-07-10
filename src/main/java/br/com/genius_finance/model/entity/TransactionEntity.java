package br.com.genius_finance.model.entity;

import br.com.genius_finance.core.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
@EqualsAndHashCode(callSuper = true)
public class TransactionEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private PersonEntity person;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

}
