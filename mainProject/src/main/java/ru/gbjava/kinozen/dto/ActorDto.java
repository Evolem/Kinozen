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
public class ActorDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String description;
    private String url;
    private Set<Content> contents;

}
