package ru.gbjava.kinozen.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.gbjava.kinozen.dto.CommentDto;
import ru.gbjava.kinozen.persistence.entities.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDto toDto(Comment comment);

    Comment toEntity(CommentDto commentDto);

    Iterable<CommentDto> toDtoList(List<Comment> comments);
}
