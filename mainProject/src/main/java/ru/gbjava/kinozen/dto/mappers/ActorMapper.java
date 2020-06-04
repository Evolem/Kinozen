package ru.gbjava.kinozen.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.gbjava.kinozen.dto.ActorDto;
import ru.gbjava.kinozen.persistence.entities.Actor;

import java.util.List;

@Mapper
public interface ActorMapper {
    ActorMapper INSTANCE = Mappers.getMapper(ActorMapper.class);

    ActorDto toDto(Actor actor);

    Actor toEntity(ActorDto actorDto);

    Iterable<ActorDto> toDtoList(List<Actor> actors);
}

