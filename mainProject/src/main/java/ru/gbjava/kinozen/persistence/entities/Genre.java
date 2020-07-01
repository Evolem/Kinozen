package ru.gbjava.kinozen.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private UUID id;

    @Column(name = "name_genre")
    private String name;

    @Column(name = "url_genre")
    private String url;

    @ManyToMany
    @JoinTable(
            name = "tbl_genre_content",
            joinColumns = @JoinColumn(name = "id_genre"),
            inverseJoinColumns = @JoinColumn(name = "id_content"))
    private Set<Content> contents;

    @ManyToMany(mappedBy = "genreSubscribeList")
    Set<User> genreSubscribers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return name.equals(genre.name) &&
                url.equals(genre.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }
}
