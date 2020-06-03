package ru.gbjava.kinozen.persistence.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_director")
    private Long idDirector;

    @Column(name = "firstname_director")
    private String firstnameDirector;

    @Column(name = "lastname_director")
    private String lastnameDirector;

    @Column(name = "description_director")
    private String descriptionDirector;

    @Column(name = "img_director")
    private String imgDirector;
}