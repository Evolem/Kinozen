package ru.gbjava.kinozen.persistence.entities.enums;

import lombok.Getter;

public enum TypeContent {

    SERIAL("Сериал"),
    FILM("Фильм");

    @Getter
    private String type;

    TypeContent(String type) {
        this.type = type;
    }
}
