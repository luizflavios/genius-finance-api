package br.com.genius_finance.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "addresses")
@EqualsAndHashCode(callSuper = true)
public class AddressEntity extends BaseEntity {

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
