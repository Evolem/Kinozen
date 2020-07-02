package ru.gbjava.kinozen.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
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

    @Column(name = "url_actor")
    private String url;

    @Column(name = "img_actor")
    private String imageName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_actor_content",
            joinColumns = @JoinColumn(name = "id_actor"),
            inverseJoinColumns = @JoinColumn(name = "id_content"))
    private Set<Content> contents;

    @ManyToMany(mappedBy = "actorSubscribeList")
    Set<User> actorSubscribers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return firstName.equals(actor.firstName) &&
                lastName.equals(actor.lastName) &&
                Objects.equals(description, actor.description) &&
                url.equals(actor.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, description, url);
    }
}
