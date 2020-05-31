package ru.gbjava.kinozen.services.pojo;

import lombok.Data;
import ru.gbjava.kinozen.persistence.entities.TypeContent;

@Data
public class TypeContentPojo {
    private Long id;
    private String name;

    public TypeContentPojo(TypeContent typeContent) {
        this.id = typeContent.getId();
        this.name = typeContent.getName();
    }
}
