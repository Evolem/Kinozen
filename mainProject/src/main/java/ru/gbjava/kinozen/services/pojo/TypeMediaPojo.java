package ru.gbjava.kinozen.services.pojo;

import lombok.Builder;
import lombok.Data;
import ru.gbjava.kinozen.persistence.entities.Media;
import ru.gbjava.kinozen.persistence.entities.TypeMedia;

@Data
public class TypeMediaPojo {
    private Integer id;
    private String name;

    public TypeMediaPojo(TypeMedia typeMedia) {
        this.id = typeMedia.getId();
        this.name = typeMedia.getName();
    }
}
