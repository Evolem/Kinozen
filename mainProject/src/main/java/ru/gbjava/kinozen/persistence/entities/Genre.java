package ru.gbjava.kinozen.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tbl_genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private Long id;

    @Column(name = "name_genre")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "tbl_genre_content",
            joinColumns = @JoinColumn(name = "id_genre"),
            inverseJoinColumns = @JoinColumn(name = "id_content"))
    Set<Content> contents;
}
