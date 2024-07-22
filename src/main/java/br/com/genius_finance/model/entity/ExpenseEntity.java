package br.com.genius_finance.model.entity;

import br.com.genius_finance.model.entity.base.AuditedBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "expenses")
@EqualsAndHashCode(callSuper = true)
public class ExpenseEntity extends AuditedBaseEntity {

    @Column(nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "expense_transactions",
            joinColumns = @JoinColumn(name = "expense_id", referencedColumnName = "id", table = "expenses"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id", referencedColumnName = "id", table = "transactions"))
    private List<TransactionEntity> transactions;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner", nullable = false)
    private PersonEntity owner;

}
