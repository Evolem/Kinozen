package ru.gbjava.kinozen.persistence.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;


@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "tbl_user")
public class User {


    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "login_user")
    private String login;


    @Column(name = "password_user")
    private String password;

    @Column(name = "name_user")
    private String name;

    @Column(name = "email_user")
    private String email;


    @ManyToMany
    @JoinTable(name="tbl_role_user", joinColumns=
    @JoinColumn(name="id_user", referencedColumnName="id_user"), inverseJoinColumns=
    @JoinColumn(name="id_role", referencedColumnName="id_role"))
    private List<Role> roles = new ArrayList<>();

}
