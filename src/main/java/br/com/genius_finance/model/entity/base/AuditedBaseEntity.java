package br.com.genius_finance.model.entity.base;

import br.com.genius_finance.model.entity.PersonEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class AuditedBaseEntity extends BaseEntity implements Serializable {

    @ManyToOne(optional = false)
    @JoinColumn(name = "created_by", nullable = false)
    private PersonEntity createdBy;

}
