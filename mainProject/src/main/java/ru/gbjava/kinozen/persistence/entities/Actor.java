package ru.gbjava.kinozen.persistence.entities;

import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_actor")
public class Actor {

    @Id
    @Column(name = "id_actor")
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "firstname_actor")
    private String firstName;

    @Column(name = "lastname_actor")
    private String lastName;

    @Column(name = "description_actor")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_actor_content",
            joinColumns = @JoinColumn(name = "id_actor"),
            inverseJoinColumns = @JoinColumn(name = "id_content")
    )
    private List<Content> contents;


}
