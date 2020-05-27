package ru.gbjava.kinozen.persistence.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */


@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "tbl_role")
public class Role {

    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "name_role")
    private String role;

}
