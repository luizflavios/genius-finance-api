package br.com.genius_finance.model.entity;

import br.com.genius_finance.core.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "people")
@EqualsAndHashCode(callSuper = true)
public class PersonEntity extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "phone_id")
    private PhoneEntity phone;

}
