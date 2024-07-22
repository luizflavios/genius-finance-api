package br.com.genius_finance.model.entity;

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
@Table(name = "incomes")
@EqualsAndHashCode(callSuper = true)
public class IncomeEntity extends BaseEntity {

    @Column(nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "income_transactions",
            joinColumns = @JoinColumn(name = "income_id", referencedColumnName = "id", table = "incomes"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id", referencedColumnName = "id", table = "transactions"))
    private List<TransactionEntity> transactions;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

}
