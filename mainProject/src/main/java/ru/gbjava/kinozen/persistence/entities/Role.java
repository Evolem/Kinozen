package ru.gbjava.kinozen.persistence.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "tbl_role")
public class Role {

    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = IDENTITY)
    private UUID id;

    @Column(name = "name_role")
    private String role;

}
