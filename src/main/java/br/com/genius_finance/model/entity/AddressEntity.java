package br.com.genius_finance.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "addresses")
@EqualsAndHashCode(callSuper = true)
public class AddressEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @Column(nullable = false)
    private String address;

    @Column(columnDefinition = "varchar(10)", nullable = false)
    private String number;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(columnDefinition = "varchar(25)", nullable = false)
    private String zipCode;

    private String complement;

    private String district;

}
