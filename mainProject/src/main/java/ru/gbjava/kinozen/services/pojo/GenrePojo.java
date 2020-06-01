package ru.gbjava.kinozen.services.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbjava.kinozen.persistence.entities.Genre;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenrePojo {
    private Long id;
    private String name;

    public GenrePojo(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }
}
