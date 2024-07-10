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
@Table(name = "phones")
@EqualsAndHashCode(callSuper = true)
public class PhoneEntity extends BaseEntity {

    @Column(name = "country_code", columnDefinition = "varchar(3)", nullable = false)
    private String countryCode;

    @Column(name = "city_code", columnDefinition = "varchar(5)", nullable = false)
    private String cityCode;

    @Column(name = "phone_number", columnDefinition = "varchar(15)", nullable = false)
    private String phoneNumber;

}
