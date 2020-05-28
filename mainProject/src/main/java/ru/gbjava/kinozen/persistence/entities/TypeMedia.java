package ru.gbjava.kinozen.persistence.entities;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "tbl_typemedia")
public class TypeMedia {

    @Id
    @Column(name = "id_typemedia")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "name_typemedia")
    private String name;

    @OneToMany(mappedBy = "typemedia")
    private List<Media> allMedia;
}
