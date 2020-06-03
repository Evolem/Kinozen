package ru.gbjava.kinozen.dto.mappers;

import org.mapstruct.factory.Mappers;
import ru.gbjava.kinozen.dto.ContentTypeDto;
import ru.gbjava.kinozen.persistence.entities.ContentType;

import java.util.List;

public interface ContentTypeMapper {
    ContentTypeMapper INSTANCE = Mappers.getMapper(ContentTypeMapper.class);
    ContentTypeDto toDto(ContentType contentType);
    ContentType toEntity(ContentTypeDto contentTypeDto);
    Iterable<ContentTypeDto> toDtoList (List<ContentType> contentTypes);
}
