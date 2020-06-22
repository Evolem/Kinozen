package ru.gbjava.kinozen.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class WishContentDto {
    private UUID id;
    private UUID idCollection;
}
