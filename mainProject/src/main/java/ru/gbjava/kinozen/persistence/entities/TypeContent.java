package ru.gbjava.kinozen.persistence.entities;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "tbl_typecontent")
public class TypeContent {

    @Id
    @Column(name = "id_typecontent")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name_typecontent")
    private String name;

    @OneToMany(mappedBy = "typeContent")
    private List<Content> allContent;
}
