package ru.gbjava.kinozen.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.gbjava.kinozen.dto.DirectorDto;
import ru.gbjava.kinozen.persistence.entities.Director;

import java.util.List;

@Mapper
public interface DirectorMapper {
    DirectorMapper INSTANCE = Mappers.getMapper(DirectorMapper.class);

    DirectorDto toDto(Director director);

    Director toEntity(DirectorDto directorDto);

    Iterable<DirectorDto> toDtoList(List<Director> directors);
}
