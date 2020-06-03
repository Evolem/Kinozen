package ru.gbjava.kinozen.dto;

import lombok.Data;

import ru.gbjava.kinozen.persistence.entities.TypeContent;

@Data
public class TypeContentDto {
    private Long id;
    private String name;

    public TypeContentDto(TypeContent typeContent) {
        this.id = typeContent.getId();
        this.name = typeContent.getName();
    }

}
