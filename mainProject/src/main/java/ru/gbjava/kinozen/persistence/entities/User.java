package ru.gbjava.kinozen.persistence.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "tbl_user")
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = IDENTITY)
    private UUID id;

    @Column(name = "login_user")
    private String login;

    @Column(name = "password_user")
    private String password;

    @Column(name = "name_user")
    private String name;

    @Column(name = "email_user")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "tbl_role_user",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;
}
