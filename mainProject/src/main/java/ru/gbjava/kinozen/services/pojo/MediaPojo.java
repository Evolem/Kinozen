package ru.gbjava.kinozen.services.pojo;

import lombok.AllArgsConstructor;
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
    private String url;

    public MediaPojo(Media media) {
        this.setId(media.getId());
        this.setName(media.getName());
        this.setReleased(media.getReleased());
        this.setDescription(media.getDescription());
        this.setVisible(media.getVisible());
        this.setUrl(media.getUrl());
    }
}
