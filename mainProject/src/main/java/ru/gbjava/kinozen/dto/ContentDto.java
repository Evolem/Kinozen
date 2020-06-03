package ru.gbjava.kinozen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbjava.kinozen.persistence.entities.Content;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {

    private Long id;
    private String name;
    private String description;
    private Date released;
    private Boolean visible;
    private TypeContentDto typeContentDto;
    private String url;

    public ContentDto(Content content) {
        this.id = content.getId();
        this.name = content.getName();
        this.released = content.getReleased();
        this.description = content.getDescription();
        this.visible = content.getVisible();
        this.typeContentDto = new TypeContentDto(content.getTypeContent());
        this.url = content.getUrl();
    }

}
