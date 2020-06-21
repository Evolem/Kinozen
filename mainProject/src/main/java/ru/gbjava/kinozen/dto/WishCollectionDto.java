package ru.gbjava.kinozen.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class WishCollectionDto {

    private UUID id;
    private String name;
    private List<UUID> contents;
}
