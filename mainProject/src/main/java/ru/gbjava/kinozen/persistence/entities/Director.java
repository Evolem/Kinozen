package ru.gbjava.kinozen.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
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

    @ManyToMany
    @JoinTable(name = "tbl_media",
            joinColumns = @JoinColumn(name = "id_media"),
            inverseJoinColumns = @JoinColumn(name = "id_director"))
    private Collection<Media> media;


    public String getFullName() {
        return String.format("%s %s", firstnameDirector, lastnameDirector);
    }
}