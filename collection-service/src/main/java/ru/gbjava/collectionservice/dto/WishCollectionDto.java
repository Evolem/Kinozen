package ru.gbjava.collectionservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class WishCollectionDto {
    private UUID id;
    private List<UUID> contents;
}
