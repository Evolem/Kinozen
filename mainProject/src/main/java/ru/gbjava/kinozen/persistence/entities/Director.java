package ru.gbjava.kinozen.persistence.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_director")
    private Long id;

    @Column(name = "firstname_director")
    private String firstName;

    @Column(name = "lastname_director")
    private String lastName;

    @Column(name = "description_director")
    private String description;

    @Column(name = "url_director")
    private String url;

    @Column(name = "img_director")
    private String imageName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_director_content",
            joinColumns = @JoinColumn(name = "id_director"),
            inverseJoinColumns = @JoinColumn(name = "id_content"))
    private Set<Content> contents;
}