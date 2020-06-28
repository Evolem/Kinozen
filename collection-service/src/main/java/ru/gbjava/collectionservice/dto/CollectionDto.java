package ru.gbjava.collectionservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Builder
public class CollectionDto {

    private UUID id;
    private String name;
    private List<UUID> contents;

}
