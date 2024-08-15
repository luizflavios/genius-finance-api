package br.com.genius_finance.model.entity;

import br.com.genius_finance.model.entity.base.AuditedBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

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

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private BigDecimal spendingLimit;

    @OneToMany
    @JoinTable(name = "budget_expenses",
            joinColumns = @JoinColumn(name = "expense_id", referencedColumnName = "id", table = "expenses"),
            inverseJoinColumns = @JoinColumn(name = "budget_id", referencedColumnName = "id", table = "budgets"))
    private List<ExpenseEntity> expenses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupEntity ownerGroup;

}
