package ru.gbjava.kinozen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SeasonDto {

    private UUID id;
    private UUID contentId;
    private Integer numberSeason;
    private String description;
    private String url;

    @Override
    public String toString() {
        return "SeasonDto{" +
                "id=" + id +
                ", contentId=" + contentId +
                ", numberSeason=" + numberSeason +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
