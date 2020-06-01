package ru.gbjava.kinozen.services.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Genre;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private List<GenrePojo> genres;

    public ContentPojo(Content content) {
        this.id = content.getId();
        this.name = content.getName();
        this.released = content.getReleased();
        this.description = content.getDescription();
        this.visible = content.getVisible();
        this.typeContentPojo = new TypeContentPojo(content.getTypeContent());
        for (Genre genre : content.getGenres()) {
            this.genres.add(new GenrePojo(genre));
        }
        this.url = content.getUrl();
    }
}
