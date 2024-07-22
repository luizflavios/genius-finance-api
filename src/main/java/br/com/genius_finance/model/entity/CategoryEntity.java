package br.com.genius_finance.model.entity;

import br.com.genius_finance.core.enums.CategoryType;
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
@Table(name = "categories")
@EqualsAndHashCode(callSuper = true)
public class CategoryEntity extends AuditedBaseEntity {

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType type;

}
