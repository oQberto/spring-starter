package org.example.spring.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.spring.database.entity.enums.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@NamedEntityGraph(
        name = "User.company",
        attributeNodes = @NamedAttributeNode("company"))
@Data
@ToString(exclude = "userChats")
@EqualsAndHashCode(of = "username")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends AuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "role")
    @Enumerated(value = STRING)
    private Role role;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();
}
