package br.com.genius_finance.model.entity;

import br.com.genius_finance.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "phones")
@EqualsAndHashCode(callSuper = true)
public class PhoneEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @Column(name = "country_code", columnDefinition = "varchar(3)", nullable = false)
    private String countryCode;

    @Column(name = "city_code", columnDefinition = "varchar(5)", nullable = false)
    private String cityCode;

    @Column(name = "phone_number", columnDefinition = "varchar(15)", nullable = false)
    private String phoneNumber;

}
