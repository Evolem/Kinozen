package ru.gbjava.kinozen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Episode;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SeasonDto {

    private UUID id;
    private Content content;
    private Integer numberSeason;
    private String description;
    private String url;
    private List<Episode> episodes;
}
