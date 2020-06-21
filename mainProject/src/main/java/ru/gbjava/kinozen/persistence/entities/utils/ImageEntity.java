package ru.gbjava.kinozen.persistence.entities.utils;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class ImageEntity {

    private String imageName;
}
