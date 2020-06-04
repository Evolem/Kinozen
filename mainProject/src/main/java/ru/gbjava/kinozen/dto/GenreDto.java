package ru.gbjava.kinozen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbjava.kinozen.persistence.entities.Content;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class GenreDto {
    private UUID id;
    private String name;
    //todo тут должен быть список dto!
    private Set<Content> contents;
    private String url;
}
