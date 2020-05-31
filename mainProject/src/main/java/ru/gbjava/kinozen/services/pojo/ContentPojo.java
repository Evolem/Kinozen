package ru.gbjava.kinozen.services.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gbjava.kinozen.persistence.entities.Content;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentPojo {

    private Long id;
    private String name;
    private String description;
    private Date released;
    private Boolean visible;
    private TypeContentPojo typeContentPojo;
    private String url;

    public ContentPojo(Content content) {
        this.id = content.getId();
        this.name = content.getName();
        this.released = content.getReleased();
        this.description = content.getDescription();
        this.visible = content.getVisible();
        this.typeContentPojo = new TypeContentPojo(content.getTypeContent());
        this.url = content.getUrl();
    }
}
