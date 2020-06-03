package ru.gbjava.kinozen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ContentTypeDto {
    private UUID id;
    private String name;
}
