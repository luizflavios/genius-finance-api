package br.com.genius_finance.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private PersonEntity person;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

}
