package ru.gbjava.collectionservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Builder
public class WishCollectionDto {

    private UUID id;
    private String name;
    private List<UUID> contents;
}
