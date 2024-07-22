package br.com.genius_finance.model.entity;

import br.com.genius_finance.core.enums.Gender;
import br.com.genius_finance.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "people")
@EqualsAndHashCode(callSuper = true)
public class PersonEntity extends BaseEntity {

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String email;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private UserEntity user;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private AddressEntity address;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private PhoneEntity phone;

}
