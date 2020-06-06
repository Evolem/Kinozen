package ru.gbjava.kinozen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbjava.kinozen.persistence.entities.Season;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EpisodeDto {

    private UUID id;
    private Season season;
    private Integer numberEpisode;
    private String name;
    private String description;
    private String imageName;
}
