package ru.gbjava.kinozen.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.gbjava.kinozen.dto.EpisodeDto;
import ru.gbjava.kinozen.persistence.entities.Episode;

import java.util.List;

@Mapper
public interface EpisodeMapper {
    
    EpisodeMapper INSTANCE = Mappers.getMapper(EpisodeMapper.class);

    EpisodeDto toDto(Episode episode);

    Episode toEntity(EpisodeDto episodeDto);

    Iterable<EpisodeDto> toDtoList(List<Episode> episodes);
}
