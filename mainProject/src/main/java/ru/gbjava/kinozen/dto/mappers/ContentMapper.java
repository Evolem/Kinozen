package ru.gbjava.kinozen.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.persistence.entities.Content;

import java.util.List;

@Mapper
public interface ContentMapper {
    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);

    ContentDto toDto(Content content);

    Content toEntity(ContentDto contentDto);

    Iterable<ContentDto> toDtoList(List<Content> contents);
}
