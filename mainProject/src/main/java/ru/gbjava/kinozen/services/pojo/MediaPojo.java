package ru.gbjava.kinozen.services.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gbjava.kinozen.persistence.entities.Media;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaPojo {

    private Integer id;
    private String name;
    private String description;
    private Date released;
    private Boolean visible;
    private TypeMediaPojo typeMediaPojo;
    private String url;

    public MediaPojo(Media media) {
        this.id = media.getId();
        this.name = media.getName();
        this.released = media.getReleased();
        this.description = media.getDescription();
        this.visible = media.getVisible();
        this.typeMediaPojo = new TypeMediaPojo(media.getTypemedia());
        this.url = media.getUrl();
    }
}
