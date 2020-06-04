package ru.gbjava.kinozen.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.gbjava.kinozen.dto.GenreDto;
import ru.gbjava.kinozen.persistence.entities.Genre;

import java.util.List;

@Mapper
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDto toDto(Genre genre);

    Genre toEntity(GenreDto genreDto);

    Iterable<GenreDto> toDtoList(List<Genre> genres);
}
