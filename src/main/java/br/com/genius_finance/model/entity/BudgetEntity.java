package br.com.genius_finance.model.entity;

import br.com.genius_finance.model.entity.base.AuditedBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "budgets")
@EqualsAndHashCode(callSuper = true)
public class BudgetEntity extends AuditedBaseEntity {

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

}
