package ru.gbjava.kinozen.persistence.entities;

import lombok.*;

import javax.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_actor")
public class Actor {

    @Id
    @Column(name = "id_actor")
    @GeneratedValue(strategy = IDENTITY)
    private UUID id;

    @Column(name = "firstname_actor")
    private String firstName;

    @Column(name = "lastname_actor")
    private String lastName;

    @Column(name = "description_actor")
    private String description;

    @Column(name = "img_actor")
    private String image;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_actor_content",
            joinColumns = @JoinColumn(name = "id_actor"),
            inverseJoinColumns = @JoinColumn(name = "id_content"))
    private Set<Content> contents;

}
