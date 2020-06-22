package ru.gbjava.kinozen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbjava.kinozen.persistence.entities.Actor;
import ru.gbjava.kinozen.persistence.entities.Director;
import ru.gbjava.kinozen.persistence.entities.Genre;
import ru.gbjava.kinozen.persistence.entities.User;
import ru.gbjava.kinozen.persistence.entities.enums.TypeContent;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ContentDto {

    private UUID id;
    private String name;
    private String description;
    private Date released;
    private Boolean visible;
    private String url;
    private String imageName;
    private TypeContent type;
    private Set<Genre> genres;
    private Set<Actor> actors;
    private Set<Director> directors;
    private Set<User> likes;
    private Set<User> dislikes;
    private String trailerLink;
}
