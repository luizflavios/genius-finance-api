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
@Table(name = "groups")
@EqualsAndHashCode(callSuper = true)
public class GroupEntity extends AuditedBaseEntity {

    @Column(nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "groups_people",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id", table = "groups"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id", table = "people"))
    private List<PersonEntity> people;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private List<BudgetEntity> budgets;

}
