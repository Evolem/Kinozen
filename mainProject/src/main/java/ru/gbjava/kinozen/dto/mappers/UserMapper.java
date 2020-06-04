package ru.gbjava.kinozen.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.gbjava.kinozen.dto.UserDto;
import ru.gbjava.kinozen.persistence.entities.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
