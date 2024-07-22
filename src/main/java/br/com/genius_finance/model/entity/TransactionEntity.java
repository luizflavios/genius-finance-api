package br.com.genius_finance.model.entity;

import br.com.genius_finance.core.enums.PaymentType;
import br.com.genius_finance.model.entity.base.AuditedBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
@EqualsAndHashCode(callSuper = true)
public class TransactionEntity extends AuditedBaseEntity {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner", nullable = false)
    private PersonEntity owner;

}
